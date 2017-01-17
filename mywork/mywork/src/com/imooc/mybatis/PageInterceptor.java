package com.imooc.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.imooc.entities.BaseDomain;
@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}),  
    @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
}) 
public class PageInterceptor implements Interceptor {
	private Object SELECT_ID = "selectpage";

	//���������еĴ��룬��������ԭ�еķ���
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		 System.out.println("PageInterceptor -- intercept");
        if (invocation.getTarget() instanceof StatementHandler) {  
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
            MappedStatement mappedStatement=(MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String selectId=mappedStatement.getId();
            if(SELECT_ID.equals(selectId.substring(selectId.lastIndexOf(".")+1).toLowerCase())){
                BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");  
                // ��ҳ������Ϊ��������parameterObject��һ������  
                String sql = boundSql.getSql();
                BaseDomain co=(BaseDomain)(boundSql.getParameterObject());
                
                // ��дsql  
                String countSql=concatCountSql(sql);
                String pageSql=concatPageSql(sql,co);
                
                System.out.println("��д�� count  sql        :"+countSql);
                System.out.println("��д�� select sql        :"+pageSql);
                
                Connection connection = (Connection) invocation.getArgs()[0];  
                
                PreparedStatement countStmt = null;  
                ResultSet rs = null;  
                int totalCount = 0;  
                try { 
                    countStmt = connection.prepareStatement(countSql);  
                    rs = countStmt.executeQuery();  
                    if (rs.next()) {  
                        totalCount = rs.getInt(1);  
                    } 
                    
                } catch (SQLException e) {  
                    System.out.println("Ignore this exception"+e);  
                } finally {  
                    try {  
                        rs.close();  
                        countStmt.close();  
                    } catch (SQLException e) {  
                        System.out.println("Ignore this exception"+ e);  
                    }  
                }  
                
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);            
              
                //��count
                co.setCount(totalCount);
            }
        } 
        
        return invocation.proceed();
	}
	//��������StatementHandler
	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {  
            return Plugin.wrap(target, this);  
        } else {  
            return target;  
        }  
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

	public String concatCountSql(String sql){
        StringBuffer sb=new StringBuffer("select count(*) from ");
        sql=sql.toLowerCase();
        
        if(sql.lastIndexOf("order")>sql.lastIndexOf(")")){
            sb.append(sql.substring(sql.indexOf("from")+4, sql.lastIndexOf("order")));
        }else{
            sb.append(sql.substring(sql.indexOf("from")+4));
        }
        return sb.toString();
    }
    
    public String concatPageSql(String sql,BaseDomain co){
        StringBuffer sb=new StringBuffer();
        sb.append(sql);
        sb.append(" limit ").append(co.getPage_begin()).append(" , ").append(co.getPage_size());
        return sb.toString();
    }
    
    public void setPageCount(){
        
    }
}

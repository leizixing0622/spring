package com.imooc.reflection;

import java.lang.reflect.Field;

public class ReflectUtil {

	/**
     * ���÷����ȡָ�������ָ������
     *
     * @param obj       Ŀ�����
     * @param fieldName Ŀ������
     * @return Ŀ�����Ե�ֵ
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * ���÷����ȡָ�����������ָ������
     *
     * @param obj       Ŀ�����
     * @param fieldName Ŀ������
     * @return Ŀ���ֶ�
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //���ﲻ������������û�и��ֶο��ܶ�Ӧ�ĸ����У���û�оͷ���null��
            }
        }
        return field;
    }

    /**
     * ���÷�������ָ�������ָ������Ϊָ����ֵ
     *
     * @param obj        Ŀ�����
     * @param fieldName  Ŀ������
     * @param fieldValue Ŀ��ֵ
     */
    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
	
}

package com.ctrip.framework.apollo.metrics.util;

import java.lang.management.ManagementFactory;
import javax.management.InstanceAlreadyExistsException;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public final class JMXUtil {
    public static ObjectName register(String name, Object mbean) {
        try {
            ObjectName objectName = new ObjectName(name);

            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();

            try {
                mbeanServer.registerMBean(mbean, objectName);
            } catch (InstanceAlreadyExistsException ex) {
                mbeanServer.unregisterMBean(objectName);
                mbeanServer.registerMBean(mbean, objectName);
            }

            return objectName;
        } catch (JMException e) {
            throw new IllegalArgumentException(name, e);
        }
    }

    public static void unregister(String name) {
        try {
            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();

            mbeanServer.unregisterMBean(new ObjectName(name));
        } catch (JMException e) {
            throw new IllegalArgumentException(name, e);
        }

    }

//    private static final String[] THROWABLE_COMPOSITE_INDEX_NAMES = {"message", "class", "stackTrace"};
//    private static final String[] THROWABLE_COMPOSITE_INDEX_DESCRIPTIONS = {"message", "class", "stackTrace"};
//    private static final OpenType<?>[] THROWABLE_COMPOSITE_INDEX_TYPES = new OpenType<?>[]{
//        SimpleType.STRING,
//        SimpleType.STRING, SimpleType.STRING};
//
//    private static CompositeType THROWABLE_COMPOSITE_TYPE;
//
//    public static CompositeType getThrowableCompositeType() throws JMException {
//        if (THROWABLE_COMPOSITE_TYPE == null) {
//            THROWABLE_COMPOSITE_TYPE = new CompositeType("Throwable", "Throwable", THROWABLE_COMPOSITE_INDEX_NAMES,
//                THROWABLE_COMPOSITE_INDEX_DESCRIPTIONS,
//                THROWABLE_COMPOSITE_INDEX_TYPES);
//        }
//
//        return THROWABLE_COMPOSITE_TYPE;
//    }
//
//    public static CompositeData getErrorCompositeData(Throwable error) throws JMException {
//        if (error == null) {
//            return null;
//        }
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("class", error.getClass().getName());
//        map.put("message", error.getMessage());
//
//        map.put("stackTrace", Utils.getStackTrace(error));
//
//        return new CompositeDataSupport(getThrowableCompositeType(), map);
//    }
}

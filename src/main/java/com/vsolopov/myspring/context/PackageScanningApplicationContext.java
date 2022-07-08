package com.vsolopov.myspring.context;

import com.vsolopov.myspring.annotation.MyBean;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public class PackageScanningApplicationContext implements  ApplicationContext{

    private final Map<String, Object> nameToBeanMap = new HashMap<>();

    @SneakyThrows
    public PackageScanningApplicationContext(String packageName) {
        //1. Scan The package to find all bean classes.
        var reflections = new Reflections(packageName);

        //2. Check if class is annotated with @BoboBean.
        var targetClasses = reflections.getTypesAnnotatedWith(MyBean.class);

        //3. If class create instances of those classes.
        for (var targetClass : targetClasses) {
            var constructor = targetClass.getConstructor();
            var beanObject = constructor.newInstance();

            //4. Resolve beanName.
            String annotationValue = targetClass.getAnnotation(MyBean.class).value();
            var beanName = annotationValue.isBlank() ? targetClass.getSimpleName() : annotationValue;

            //5. Store bean object by its name.
            nameToBeanMap.put(beanName, beanObject);
        }
    }

    public <T> T getBean(Class<T> beanType) {
        return nameToBeanMap.values().stream()
                .filter(bean -> bean.getClass().isAssignableFrom(beanType))
                .findAny()
                .map(beanType::cast)
                .orElseThrow();
    }

    public Object getBean(String beanName) {
        return nameToBeanMap.get(beanName);
    }

    public <T> T getBean(String beanName, Class<T> beanType) {
        var bean = nameToBeanMap.get(beanName);
        return beanType.cast(bean);
    }

}

package com.atguigu.lease.web.admin.custom.converter;

import com.atguigu.lease.model.enums.BaseEnum;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {

        return source -> {
            T[] enumConstants = targetType.getEnumConstants();
            for (T enumConstant : enumConstants){
                if(enumConstant.getCode().equals(Integer.valueOf(source))){
                    return enumConstant;
                }
            }
            try {
                throw new IllegalAccessException("非法枚举类：" + source );
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };



    }
}

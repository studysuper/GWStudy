package com.bootdo.common.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName CbsCode
 * @Description 实现thymeleaf 自定义标签
 * <p>
 * 样例： <th:value aa="22"/> 分别表示为：方言，方言标签，标签属性
 * @Author ZQ
 * @Date 2019/3/26 13:52
 */
public class CbsCode extends AbstractDialect {

    private static final String DIALECT_NAME = "cbs dialect";


    public CbsCode() {
        super(DIALECT_NAME);
    }

    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new MatterTagProcessor(dialectPrefix));
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }

}

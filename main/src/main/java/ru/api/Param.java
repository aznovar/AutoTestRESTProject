package main.java.ru.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class Param {
    private Map<String, String> data;
    private Integer maxLen;
    private String name;
    private String postfix;
    private String prefix;

    private Param(ParamBuilder builder) {
        this.data = builder.data;
        this.name = builder.name;
        this.prefix = builder.prefix;
        this.postfix = builder.postfix;
        this.maxLen = builder.maxLen;
    }

    public static String genAutoName() {
        return genAutoName(15);
    }

    public static String genAutoName(int maxLen) {
        return new Param.ParamBuilder()
                .maxLen(maxLen)
                .randomize()
                .build()
                .getName();
    }

    public static ParamBuilder builder() {
        return new ParamBuilder();
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }

    public Map<String, String> getData() {
        return data;
    }

    // METHODS PUBLIC
    public void addData(String k, String v) {
        data.put(k, v);
    }

    public void clearData() {
        data.clear();
    }

    public String replaceParamGenType(String inputWithParams) {
        return StrSubstitutor.replace(inputWithParams, data);
    }

    public int getMaxLen() {
        return maxLen;
    }

    public static class ParamBuilder {
        private Map<String, String> data;
        private Integer maxLen;
        private String name;
        private String postfix;
        private String prefix;

        public ParamBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ParamBuilder prefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public ParamBuilder postfix(String postfix) {
            this.postfix = postfix;
            return this;
        }

        public ParamBuilder maxLen(int maxLen) {
            this.maxLen = maxLen;
            return this;
        }

        public ParamBuilder data(Map<String, String> data) {
            this.data = data;
            return this;
        }

        public Param build() {
            return new Param(this);
        }

        public ParamBuilder randomize() {
            prefix = "JDAUTO_";
            postfix = "";
            data = new HashMap<>();
            return generate();
        }

        public ParamBuilder generate() {
            maxLen = maxLen == null ? 10 : maxLen;
            name = generateRandomAlphanumericString();
            return this;
        }

        public String generateRandomAlphanumericString() {
            int len = RandomUtils.nextInt(10, maxLen + 10);
            return prefix + RandomStringUtils.randomAlphanumeric(len);
        }

    }

}
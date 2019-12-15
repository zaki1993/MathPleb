package com.zaki.mathpleb.internal.lang.operator;

import com.zaki.mathpleb.internal.lang.operator.impl.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OperatorBuilder {

    private static final Map<String, Class> operatorDefs = new HashMap<>();

    static {
        operatorDefs.put("\\+", AddOperator.class);
        operatorDefs.put("\\-", SubtractOperator.class);
        operatorDefs.put("\\*", MultiplyOperator.class);
        operatorDefs.put("\\/", DivideOperator.class);
        operatorDefs.put("\\%", ModuloOperator.class);
        operatorDefs.put("\\^", PowerOperator.class);
        operatorDefs.put("sin\\(.*\\)", SinOperator.class);
        operatorDefs.put("cos\\(.*\\)", CosOperator.class);
        operatorDefs.put("tg\\(.*\\)", TgOperator.class);
        operatorDefs.put("cotg\\(.*\\)", CotgOperator.class);
        operatorDefs.put("abs\\(.*\\)", AbsOperator.class);
    }

    private OperatorBuilder() {

    }

    public static Operator build(String sign) {

        Operator result = null;

        try {
            List<Map.Entry<String, Class>> matches = operatorDefs.entrySet()
                                                                 .stream()
                                                                 .filter(operator -> sign.matches(operator.getKey()))
                                                                 .collect(Collectors.toList());

            if (matches != null && !matches.isEmpty()) {
                // get the first operator that matches the sign
                Class operatorDef = matches.get(0).getValue();
                if (operatorDef != null) {
                    result = (Operator) operatorDef.getConstructor().newInstance();
                }
            }
        } catch (Exception e) {
            // TODO
        }

        return result;
    }
}

package 算法.数学.其他;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class _65有效数字 {
    /*
    有效数字（按顺序）可以分成以下几个部分：

    一个 小数 或者 整数
    （可选）一个 'e' 或 'E' ，后面跟着一个 整数
    小数（按顺序）可以分成以下几个部分：

    （可选）一个符号字符（'+' 或 '-'）
    下述格式之一：
    至少一位数字，后面跟着一个点 '.'
    至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
    一个点 '.' ，后面跟着至少一位数字
    整数（按顺序）可以分成以下几个部分：

    （可选）一个符号字符（'+' 或 '-'）
    至少一位数字
    部分有效数字列举如下：["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]

    部分无效数字列举如下：["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]

    给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
     */

    /**
     <h1>正则表达式匹配</h1>
     */
    public boolean isNumber1(String s) {
        String sign = "[+-]?";//可选正负号
        String num = "(" + sign + "(\\d+(\\.\\d*)?|(\\.\\d+)))";//有效整数或小数
        Pattern pattern = Pattern.compile(num + "([eE]" + sign + "\\d+)?");//可选指数部分
        return pattern.matcher(s).matches();
    }

    /**
     <h1>逻辑分解</h1>
     */
    public boolean isNumber2(String s) {
        boolean isNum = false, isDecimal = false, isE = false;
        int len = s.length() - 1;
        for (int i = 0; i <= len; i++) {
            char tmp = s.charAt(i);
            if ('0' <= tmp && tmp <= '9') {
                isNum = true;
            } else if (tmp == '.') {
                // case1:已经是小数还出现小数点
                // case2:前面不是数字,但小数点出现在末尾
                // case3:前面有E,还在后面出现小数
                if (isDecimal || (!isNum && i == len) || isE)
                    return false;
                isDecimal = true;
            } else if (tmp == 'e' || tmp == 'E') {
                // case1:已经有e出现过
                // case2:前面不是数字
                // case3:e后面没有数字了
                if (isE || !isNum || i == len)
                    return false;
                isE = true;
            } else if (tmp == '-' || tmp == '+') {
                // case1:如果符号有前一位但符号的前面一位不是e
                // case2:符号出现在末尾
                if ((i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') || i == len)
                    return false;
            } else {//遇到莫名其妙的字符
                return false;
            }
        }
        return true;
    }

    /**
     <h1>状态自动机</h1>
     <h2>预备知识</h2>
     确定有限状态自动机（以下简称「自动机」）是一类计算模型。<br>
     它包含一系列状态，这些状态中：<br>
     有一个特殊的状态，被称作「初始状态」。<br>
     还有一系列状态被称为「接受状态」，它们组成了一个特殊的集合。<br>
     其中，一个状态可能既是「初始状态」，也是「接受状态」。<br>
     <br>
     起初，这个自动机处于「初始状态」。<br>
     随后，它顺序地读取字符串中的每一个字符，并根据当前状态和读入的字符，按照某个事先约定好的「转移规则」，从当前状态转移到下一个状态；<br>
     当状态转移完成后，它就读取下一个字符。<br>
     当字符串全部读取完毕后，如果自动机处于某个「接受状态」，则判定该字符串「被接受」；否则，判定该字符串「被拒绝」。
     <br>
     注意：如果输入的过程中某一步转移失败了，即不存在对应的「转移规则」，此时计算将提前中止。<br>
     在这种情况下我们也判定该字符串「被拒绝」。<br>
     <br>
     一个自动机，总能够回答某种形式的「对于给定的输入字符串 S，判断其是否满足条件 P」的问题。在本题中，条件 P 即为「构成合法的表示数值的字符串」。<br>
     自动机驱动的编程，可以被看做一种暴力枚举方法的延伸：它穷尽了在任何一种情况下，对应任何的输入，需要做的事情。<br>
     自动机在计算机科学领域有着广泛的应用。在算法领域，它与大名鼎鼎的字符串查找算法「KMP 算法」有着密切的关联；在工程领域，它是实现「正则表达式」的基础。
     */
    public boolean isNumber3(String s) {
        //状态转移图-->enum State
        //State -> StateMap
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        //设置状态Map, currentStateMap: validCharType -> currentState.nextState
        Map<CharType, State> initialMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        }};
        transfer.put(State.STATE_INITIAL, initialMap);
        Map<CharType, State> intSignMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);
        Map<CharType, State> integerMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);
        Map<CharType, State> pointMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_POINT, pointMap);
        Map<CharType, State> pointWithoutIntMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
        Map<CharType, State> fractionMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);
        Map<CharType, State> expMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);
        Map<CharType, State> expSignMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);
        Map<CharType, State> expNumberMap = new HashMap<>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < length; i++) {
            CharType type = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(type)) {
                //当前类型在当前状态下是非法的
                return false;
            }
            //状态跳转
            state = transfer.get(state).get(type);
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    enum State {
        STATE_INITIAL,// 初始状态
        STATE_INT_SIGN, //符号位
        STATE_INTEGER, //整数部分
        STATE_POINT,  //左侧有整数的小数点
        STATE_POINT_WITHOUT_INT, //左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
        STATE_FRACTION, //小数部分
        STATE_EXP, //字符 e
        STATE_EXP_SIGN,  //指数部分的符号位
        STATE_EXP_NUMBER, //指数部分的整数部分
        STATE_END //字符串结束
    }

    /*
       ┌───────────────────┐   ┌────────────┬───────────────────────────┐  ┌────────────────────┐
       │                   ↓   │            │                           ↓  │                    ↓
    初始状态 -->  符号位  --> 整数部分  --> 小数点(左有整数) -->  小数部分  -->  字符e --> 指数符号 --> 指数数字 --> 结束
       │         │           ◎                              ↑ ◎                                 ◎
       │         │                                          │
       └─────────┴─────→小数点(左无整数) ──────────────────────┘

     */
    enum CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_ILLEGAL
    }

    /**
     <h1>数运算模拟</h1>
     case1: 有多个e,返回false<br>
     case2: 有一个e,检查e的两边是否符合要求<br>
     case3: 没有e,检查整个数<br>
     */
    public boolean isNumber4(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        //检查e的出现位置
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (cs[i] == 'e' || cs[i] == 'E') {
                if (idx == -1) idx = i;
                else return false;//有多个e
            }
        }
        boolean ans = true;
        if (idx != -1) {//有e
            //检查e的两边
            //左边要为整数或小数,右边必须为整数
            return check(cs, 0, idx - 1, false) && check(cs, idx + 1, n - 1, true);
        }
        //没有e
        return check(cs, 0, n - 1, false);//整数或小数
    }

    /**
     检查一个数是否符合要求(整数/小数)

     @param cs          数
     @param start       起始索引
     @param end         结束索引
     @param mustInteger 是否必须为整数
     */
    boolean check(char[] cs, int start, int end, boolean mustInteger) {
        if (start > end) return false;
        if (cs[start] == '+' || cs[start] == '-') start++;
        boolean hasDot = false, hasNum = false;
        for (int i = start; i <= end; i++) {
            if (cs[i] == '.') {
                if (mustInteger || hasDot) return false;
                hasDot = true;
            } else if (cs[i] >= '0' && cs[i] <= '9') {
                hasNum = true;
            } else {
                return false;
            }
        }
        return hasNum;
    }
}

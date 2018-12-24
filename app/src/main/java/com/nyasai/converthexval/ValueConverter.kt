package com.nyasai.converthexval

data class ConvertResult(var rslt: Boolean, var str: String)

/**
 * 数値変換
 */
abstract class ValueConverter(convertValueStr: String, isSigned: Boolean) {
    // 変換対象文字列
    var mConvertValueStr: String = convertValueStr;
    // 符号情報
    var mIsSigned:Boolean = isSigned;

    /**
     * 変換実行
     */
    fun convert(): ConvertResult{
        var chkResult = chkConvertText(this.mConvertValueStr);
        var resultStr = "0"
        if(chkResult)
        {
            // 変換処理実行
            resultStr = execConvert();
        }
        return ConvertResult(chkResult,resultStr);
    }

    /**
     * 変換データ確認
     */
    abstract fun chkConvertText(str: String): Boolean;

    /**
     * 変換処理実行
     */
    abstract fun execConvert(): String;

}
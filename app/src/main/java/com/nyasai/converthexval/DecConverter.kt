package com.nyasai.converthexval


class DecConverter(convertValueStr: String, isSigned: Boolean) : ValueConverter(convertValueStr, isSigned) {

    /**
     * 変換データ確認
     */
    override fun chkConvertText(str: String): Boolean {
        return (!str.isNullOrEmpty() && (str.toLong() < Int.MAX_VALUE) && (str.toLong() > Int.MIN_VALUE));
    }

    /**
     * 変換処理実行
     */
    override fun execConvert(): String {
        // 16進数へ変換
        var str = this.mConvertValueStr.toInt().toString(16)
        return "0x" + str.toUpperCase();
    }
}
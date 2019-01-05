package com.nyasai.converthexval


class DecConverter(convertValueStr: String, isSigned: Boolean) : ValueConverter(convertValueStr, isSigned) {

    /**
     * 変換データ確認
     */
    override fun chkConvertText(str: String): Boolean {
        if(!str.isNullOrEmpty()) {
            // 符号ありの場合
            if(this.mIsSigned){
                if(str.toLong() <= Int.MAX_VALUE && str.toLong() >= Int.MIN_VALUE)
                    return true;
            }
            else{
                if(str.toLong() <= 0xFFFFFFFF)
                    return true;
            }
        }
        return false;
    }

    /**
     * 変換処理実行
     */
    override fun execConvert(): String {
        // 16進数へ変換
        var str = java.lang.Long.toHexString(this.mConvertValueStr.toLong());
        // 4byteサイズに表示文字を縮める
        if(str.length > 8){
            var rtnStr = ""
            var strLength = str.length
            for (i in (strLength-8)..(strLength-1)) {
                rtnStr+= str[i]
            }
            return rtnStr.toUpperCase();
        }
        return str.toUpperCase();
    }
}
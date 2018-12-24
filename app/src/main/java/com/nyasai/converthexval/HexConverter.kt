package com.nyasai.converthexval

class HexConverter(convertValueStr: String, isSigned: Boolean) : ValueConverter(convertValueStr, isSigned) {

    /**
     * 変換データ確認
     */
    override fun chkConvertText(str: String): Boolean {
        // 有効なデータであるか
        return (!str.isNullOrEmpty() && str.indexOf("0x",0) != -1 && str.length >=3);
    }

    /**
     * 変換処理実行
     */
    override fun execConvert(): String {
        // 0xを削除した文字列を生成
        var cnvStr = "";
        for (i in 2..(this.mConvertValueStr.length-1)){
            cnvStr += this.mConvertValueStr[i];
        }
        // 10進数へ変換
        var value = Integer.parseInt(cnvStr,16);
        // 符号bit確認
        var tmpValue = Integer.parseInt(this.mConvertValueStr[2].toString(),16);
        var sinBit = tmpValue and 0x08;
        // unsignedかつ符号bitが1のため，変換実施
        if (sinBit != 0 && this.mIsSigned != false) {
            // 負数のため，10進数へ戻す
            var xorHexStr =""
            for (i in 2..(this.mConvertValueStr.length-1)){
                xorHexStr += "F";
            }
            var xorValue = Integer.parseInt(xorHexStr,16);
            // 1の補数
            value = value xor xorValue;
            // 1を足す
            value += 1;
            // 負数のため-1をかけて負数にする
            value *= -1;
            return value.toString();
        }
        // unsignedまたは符号bitが0なら正数のため，そのまま結果を返す
        return value.toString();
    }

}
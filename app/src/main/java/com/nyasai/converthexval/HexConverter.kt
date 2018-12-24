package com.nyasai.converthexval

class HexConverter(convertValueStr: String, isSigned: Boolean) : ValueConverter(convertValueStr, isSigned) {

    /**
     * 変換データ確認
     */
    override fun chkConvertText(str: String): Boolean {
        // 有効なデータであるか
        if(!str.isNullOrEmpty() && str.indexOf("0x",0) != -1 && str.length >=3)
        {
            // 16進数の有効な文字が
            for (i in 2..(this.mConvertValueStr.length-1)) {
                var ascii = this.mConvertValueStr[i].toUpperCase().toInt();
                // 0-9か
                if(ascii < 48 || ascii > 57)
                    if(ascii < 65 || ascii > 70)
                        return false;
            }
            return true;
        }
        return false;
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
        var value = cnvStr.toLong(16);
        // 符号bit確認
        var tmpValue = this.mConvertValueStr[2].toString().toInt(16);
        var sinBit = tmpValue and 0x08;
        // unsignedかつ符号bitが1のため，変換実施
        if (sinBit != 0 && this.mIsSigned != false) {
            // 負数のため，10進数へ戻す
            var xorHexStr =""
            for (i in 2..(this.mConvertValueStr.length-1)){
                xorHexStr += "F";
            }
            var xorValue = xorHexStr.toLong(16);
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
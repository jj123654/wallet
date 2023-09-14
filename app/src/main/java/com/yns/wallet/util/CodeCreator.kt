package com.yns.wallet.util

import android.graphics.Bitmap
import android.text.TextUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Hashtable

/**
 * 二维码生成器
 */
object CodeCreator {

    /**
     * 设置显示二维码
     */
    @JvmStatic
    fun setQRCode(activity: AppCompatActivity, code: String, imageView: ImageView) {
        activity.lifecycleScope.launch {
            val bitmap = withContext(Dispatchers.IO) {
                createQRCode(code, 400, 400)
            }
            imageView.setImageBitmap(bitmap)
        }
    }

    /**
     * 设置显示二维码
     */
    @JvmStatic
    fun setQRCode(fragment: Fragment, code: String, imageView: ImageView) {
        fragment.lifecycleScope.launch {
            val bitmap = withContext(Dispatchers.IO) {
                createQRCode(code, 400, 400)
            }
            imageView.setImageBitmap(bitmap)
        }
    }

    /*生成二维码*/
    fun createQRCode(content: String?, w: Int, h: Int): Bitmap? {
        if (TextUtils.isEmpty(content)) {
            return null
        }
        /*偏移量*/
        val offsetX = w / 2
        val offsetY = h / 2


        /*指定为UTF-8*/
        val hints = Hashtable<EncodeHintType, Any?>()
        hints[EncodeHintType.CHARACTER_SET] = "utf-8"
        //容错级别
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
        //设置空白边距的宽度
        hints[EncodeHintType.MARGIN] = 0
        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        var matrix: BitMatrix? = null
        return try {
            matrix = MultiFormatWriter().encode(
                content,
                BarcodeFormat.QR_CODE, w, h, hints
            )

            // 二维矩阵转为一维像素数组,也就是一直横着排了
            val pixels = IntArray(w * h)
            for (y in 0 until h) {
                for (x in 0 until w) {
                    if (matrix[x, y]) {
                        pixels[y * w + x] = -0x1000000
                    } else {
                        pixels[y * w + x] = -0x1
                    }
                }
            }
            val bitmap = Bitmap.createBitmap(
                w, h,
                Bitmap.Config.ARGB_8888
            )
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
            bitmap
        } catch (e: WriterException) {
            print(e)
            null
        }
    }

}
package com.example.project003

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_ctn.*
import java.io.ByteArrayOutputStream
import kotlin.random.Random
import android.graphics.drawable.Drawable
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)


        if(intent.extras != null){
            var question = intent.extras!!.get("question").toString() + " ?"
            tv_question.text = question

            var answer = getRandomAnswer()

//
//            var imageId = resources.getIdentifier(answer[0],"drawable",packageName)
//
//            iv_friend_ic.setImageResource(imageId)
//
//            tv_answer.text = answer[1]

            val res = resources
            val mDrawableName = ""+answer[0]
            val resID = res.getIdentifier(mDrawableName, "drawable", packageName)
            Log.e("RES",""+resID)
            val drawable = res.getDrawable(resID)
            iv_friend_ic.setImageResource(resID)
            tv_answer.setText(answer[1])

        }

        btn_play_again.setOnClickListener {
            finish()
        }

//        iv_friend_ic.setImageResource((R.drawable.think))


    }

    private fun getRandomAnswer():List<String>{
        var answerList = HashMap<String,List<String>>()
        answerList.put("0",listOf("meme1","ใครบอกเขิน! ไม่มี๊!"))
        answerList.put("1",listOf("meme2","ไม่ได้ยินอะไรทั้งนั้น"))
        answerList.put("2",listOf("meme3","ทางนี้โอเคจ้า"))
        answerList.put("3",listOf("meme4","โลกนี้มันมืดไปหมด"))
        answerList.put("4",listOf("meme5","ใครไหวไปก่อนเลย"))
        answerList.put("5",listOf("meme6","ไม่อยากจะพูดหรอกนะ"))
        answerList.put("6",listOf("meme7","แล้วแต่เธอเลยจ้า"))
        answerList.put("7",listOf("meme8","อย่าห้ามกู"))
        answerList.put("8",listOf("meme9","จะเล่นอะไรก็เล่น"))
        answerList.put("9",listOf("meme10","พอเห้อะ!"))

        var random = Random.nextInt(0,answerList.size)
        var answer = answerList[random.toString()]

        return answer!!
    }

    private fun shareImage(imageUri: Uri){
        val share = Intent(Intent.ACTION_SEND)
        share.putExtra(Intent.EXTRA_TEXT,"PueanTerNgi")
        share.putExtra(Intent.EXTRA_STREAM,imageUri)
        share.type = "image/*"
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(share,"Share Image"))
    }

    private fun getBitmapFromView(view: View):Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if(bgDrawable != null){
            bgDrawable!!.draw(canvas)
        }else{
            canvas.drawColor(Color.WHITE)
        }

        view.draw(canvas)
        return returnedBitmap
    }

    private fun getUriFromBitmap(bitmap:Bitmap): Uri{
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes)
        val path = MediaStore.Images.Media.insertImage(this.contentResolver,bitmap,"PueanTerNgi",null)
        return Uri.parse(path)
    }


}
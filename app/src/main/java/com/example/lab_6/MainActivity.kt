package com.example.lab_6

import android.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab_6.ui.theme.Lab_6Theme
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab_6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    {
        "message": "https://images.dog.ceo/breeds/waterdog-spanish/20181023_072736.jpg",
        "status": "success"
    }
    private var catImageView: ImageView? = null

    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catImageView = findViewById(R.id.catImageView)

        MainActivity.CatImages().execute("https://images.dog.ceo/breeds/waterdog-spanish/20181023_072736.jpg")
    }

    private inner class CatImages : AsyncTask<String?, Int?, Bitmap?>() {
        private var catImageBitmap: Bitmap? = null

        protected override fun doInBackground(vararg urls: String): Bitmap? {
            val urlString = urls[0]
            var result: Bitmap? = null
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                result = BitmapFactory.decodeStream(input)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                catImageBitmap = result

                catImageView!!.setImageBitmap(catImageBitmap)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab_6Theme {
        Greeting("Android")
    }
}


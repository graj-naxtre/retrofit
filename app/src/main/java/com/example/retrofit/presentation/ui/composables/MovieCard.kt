package com.example.retrofit.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.retrofit.data.model.Result
import com.example.retrofit.R

@Composable
fun MovieCard(movieData: Result) {
    val context = LocalContext.current
    val painter = remember {
        ImageRequest.Builder(context)
            .data(movieData.imageUrl?.let { if (it.isNotEmpty()) it[0] else R.drawable.fallback})
            .crossfade(true)
            .build()
    }
    Column(modifier = Modifier.wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = painter,
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(
                RoundedCornerShape(10.dp)
            )
        )
        Text(text = movieData.title!!)
    }
}
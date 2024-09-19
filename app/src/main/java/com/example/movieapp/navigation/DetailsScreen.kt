package com.example.movieapp.navigation

import android.text.style.ForegroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movieapp.models.Detailes
import com.example.movieapp.viewModel.MovieViewModels

@Composable
fun DetailsScreen(id: Int) {
    val movieViewModels = viewModel<MovieViewModels>()
    movieViewModels.id = id
    movieViewModels.getDetailsById()
    val state = movieViewModels.state
    val details = state.detailsData

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
        ){
        BackGroundPoster(details = details)
        ForegroundPoster(details = details)
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {
            Text(
                text = details.title,
                modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
                fontSize = 38.sp,
                color = Color.White,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
            Rating(details = details, modifier = Modifier)
            TextBulder(icon = Icons.Default.Info , title = "Summery : " , bodyText = details.plot)
            TextBulder(icon = Icons.Default.Person , title = "Actors : " , bodyText = details.actors)
            ImageRow(details = details)
        }



    }


}
@Composable
fun BackGroundPoster(details: Detailes){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){
        AsyncImage(
            model = details.poster, contentDescription = details.title ,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.6f)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.Transparent, Color.DarkGray)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }

}
@Composable
fun ForegroundPoster(details: Detailes){

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(250.dp)
            .padding(top = 80.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ){
        AsyncImage(model = details.poster, contentDescription = details.title,
            modifier = Modifier
                .width(250.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .width(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        )

    }
}
@Composable
fun Rating(details: Detailes, modifier: Modifier){
    Row(
        modifier.fillMaxWidth()
        , horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = Color.White)
        Text(
            text = details.rated,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier.width(25.dp))
        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "", tint = Color.White)
        Text(
            text = details.runtime,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier.width(25.dp))
        Icon(imageVector = Icons.Default.DateRange, contentDescription = "", tint = Color.White)
        Text(
            text = details.released,
            modifier.padding(start = 6.dp),
            color = Color.White
        )


    }
}

@Composable
fun TextBulder(icon:ImageVector , title:String, bodyText:String ){
    Row {
        Icon(imageVector = icon, contentDescription = "" , tint = Color.White)
        Text(
            text = title,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Text(text = bodyText, color = Color.White)
}
@Composable
fun ImageRow(details: Detailes){
    if (details.images.isNotEmpty()){
        LazyRow {
            items(details.images.size){
                AsyncImage(
                    model = details.images[it], contentDescription = "",
                    modifier = Modifier
                        .padding(6.dp)
                        .height(70.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                
            }
        }
    }
    
}
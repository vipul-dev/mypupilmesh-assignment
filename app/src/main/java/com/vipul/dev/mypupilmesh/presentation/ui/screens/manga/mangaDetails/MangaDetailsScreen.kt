package com.vipul.dev.mypupilmesh.presentation.ui.screens.manga.mangaDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vipul.dev.mypupilmesh.R

@Composable
fun MangaDetailsScreen(modifier: Modifier = Modifier, mangaId: Int?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top
        ) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .width(140.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )


            Column(modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp, start = 10.dp)) {

                Text(
                    "Delivery Man from Murim Id:- ${mangaId}",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Delivery Man from Murim. Delivery Man from Murim.Delivery Man from Murim.Delivery Man from Murim.Delivery Man from Murim. ",
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )


            }

        }

        Spacer(modifier = Modifier.height(20.dp))


        Text(
            "The marital god of the Murim becomes a super-fast delivery man! ",
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }


}


@Composable
@Preview(showBackground = true)
fun PreviewMangaDetailScreen() {
    MangaDetailsScreen(mangaId = 0)
}
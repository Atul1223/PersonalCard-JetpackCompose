package com.example.personalcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.personalcard.ui.theme.PersonalCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateCard()
                }
            }
        }
    }
}


@Composable
fun CreateCard() {
    val buttonClickedState =  remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(400.dp)
            .padding(20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                CreateProfileImage()
                Divider(
                    thickness = 2.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(5.dp)
                        .shadow(10.dp, RectangleShape, true, Color.Black, Color.Black)
                )
                CreateInfo()
                Box(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                },
                    modifier = Modifier.size(120.dp,50.dp),
                    shape = RoundedCornerShape(5.dp),

                ) {
                    Text(text = "Portfolio",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                if(true){
                    Content()
                }
                else{
                    Box() {
                        
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun Content() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(10.dp),
    ){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Portfolio(data = listOf(
                PortoflioProjects(R.drawable.rock_project,"Project Rock","Project Rock redefines strength, one relentless step at a time."),
                PortoflioProjects(R.drawable.cyborg_project,"Hello Cyborg","Blurring the line between humanity and technology, forging a new era of augmented existence."),
                PortoflioProjects(R.drawable.android_project,"Android World","In the vast expanse of the android world, circuits hum with artificial intelligence and dreams of electric sheep."),
                PortoflioProjects(R.drawable.cloud_project,"Raining Clouds","Unleashing the power of the virtual skies, revolutionizing connectivity and scalability.")
            ))
        }
    }
}

@Composable
private fun Portfolio(data: List<PortoflioProjects>) {
    LazyColumn(modifier = Modifier.background(Color.Transparent)){
        items(data) {item ->
            ContentItem(portfolioProjects = item)
            Divider(thickness = 1.dp, color = Color.Black, modifier = Modifier.padding(horizontal = 10.dp))
        }
    }
}

@Composable
private fun ContentItem(portfolioProjects: PortoflioProjects) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.White),
        shape = RectangleShape,
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(modifier = Modifier
                .padding(5.dp)
                .size(100.dp),
                shape = CircleShape,
                tonalElevation = 1.dp,
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Image(painter = painterResource(id = portfolioProjects.id) ,
                    contentDescription = portfolioProjects.title,
                    contentScale = ContentScale.Crop,
                )
            }
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = portfolioProjects.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                Text(text = portfolioProjects.subTitle,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Atul Kumar",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Android Developer",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun CreateProfileImage() {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(10.dp),
        shape = CircleShape,
        border = BorderStroke(2.dp, Color.Black),
        tonalElevation = 1.dp,
        ) {
        Image(
            painter = painterResource(id = R.drawable.cyborg),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(140.dp)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PersonalCardTheme {
        CreateCard()
    }
}
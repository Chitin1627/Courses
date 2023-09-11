package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseApp()
                }
            }
        }
    }
}

@Composable
fun CourseApp() {
    CoursesList(
        courseList = DataSource.topics,
    )
}
@Composable
fun CoursesList(courseList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(courseList) {course ->
                SingleCard(
                    course = course,
                    modifier = modifier
                        .padding(
                            bottom = 8.dp,
                            start = 8.dp,
                            top = 8.dp,
                            end = 8.dp)
                )
            }
        }
    )
}
@Composable
fun SingleCard(course: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = course.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 68.dp, width = 68.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = course.stringResourceId),
                    style = MaterialTheme.typography.titleSmall
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Text(
                        text = course.numOfCourses.toString(),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    CourseApp()
}
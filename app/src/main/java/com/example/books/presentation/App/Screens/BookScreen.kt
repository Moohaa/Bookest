package com.example.books.presentation.App.Screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.R
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookScreen(
    navController: NavController,
    book_id : String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }

            )
        },

    )
    {
        val scaffoldState = rememberBottomSheetScaffoldState()
        val scope = rememberCoroutineScope()
        BottomSheetScaffold(
            // Defaults to BottomSheetScaffoldDefaults.SheetPeekHeight
            sheetPeekHeight = 50.dp,

            // Defaults to true
            sheetGesturesEnabled = false,

            scaffoldState = scaffoldState,

            sheetContent = {
                // Sheet content
                Column(
                    modifier=Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp)
                            .background(Color.LightGray),

                    ) {

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.padding(20.dp,5.dp)){
                            Icon(
                                modifier=Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24), contentDescription ="ff" )
                        }
                        Text(text = "find it online")
                        
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.padding(20.dp,5.dp)){
                            Icon(
                                modifier=Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24), contentDescription ="ff" )
                        }
                        Text(text = "find it online")

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.padding(20.dp,5.dp)){
                            Icon(
                                modifier=Modifier.size(40.dp),
                                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24), contentDescription ="ff" )
                        }
                        Text(text = "find it online")

                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text={
                        Text("")
                    },
                    icon={
                        Icon(
                            modifier=Modifier.size(40.dp),
                            painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24), contentDescription ="ff" )
                    }
                    ,
                    onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.apply {
                                if (isCollapsed) expand() else collapse()
                            }
                        }
                    }
                )
            }



        ) {
            // Screen content
            Box(
                 modifier = Modifier.fillMaxSize()
            ) {
                var scrollState= rememberScrollState()
                Column(
                    modifier= Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp, 0.dp, 60.dp)
                        .verticalScroll(scrollState),

                ) {
                    Column(
                        modifier=Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter("https://storage.googleapis.com/du-prd/books/images/9780385549325.jpg"),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(0.dp, 4.dp)
                                .clickable {
                                    navController.navigate("book/88")
                                }
                        )
                        Text(
                            style= MaterialTheme.typography.h5,
                            text = "The man from the moon"
                        )
                        Text(
                            textAlign = TextAlign.Center,
                            text = "       the man from the moon took about the problems facing mans from other planite espicialy the moon wich make sense most of the time"
                        )

                    }
                    Divider()
                    Text(
                        style=MaterialTheme.typography.h6,
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        text = "Author :"
                    )
                    val padding=20
                    chips(string = "Mohamed Boukedir" , padding = padding)

                    Divider()
                    Text(
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        style=MaterialTheme.typography.h6,
                        text = "Contributors :"
                    )

                    chips(string = "Mohamed" , padding = padding)
                    chips(string = "Admine" , padding = padding)


                    Divider()
                    Text(
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        style=MaterialTheme.typography.h6,
                        text = "Publisher :"
                    )
                    chips(string = "Siriai" , padding = padding)
                    Divider()
                    Text(
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        style=MaterialTheme.typography.h6,
                        text = "Publisher :"
                    )
                    chips(string = "Siriai" , padding = padding)
                    Divider()
                    Text(
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        style=MaterialTheme.typography.h6,
                        text = "Publisher :"
                    )
                    chips(string = "Siriai" , padding = padding)
                }

              }
            }
    }
}

@Composable
fun chips(
    string :String,
    padding :Int
){
    Box(modifier = Modifier
        .padding(20.dp, 3.dp, 0.dp, 3.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colors.secondary)
    ){
        Text(
            modifier=Modifier.padding(5.dp),
            text = string
        )
    }
}
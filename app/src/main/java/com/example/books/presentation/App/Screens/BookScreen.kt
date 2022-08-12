package com.example.books.presentation.App.Screens

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.books.R
import com.example.books.data.Remote.DTO.Book
import com.example.books.presentation.App.Components.Topbar
import com.example.books.presentation.App.ViewModels.BookViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookScreen(
    navController: NavController,
    book :Book,
    viewModel :BookViewModel= hiltViewModel(),

) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    Scaffold(
        topBar = {
           Topbar(navController = navController,viewModel,book)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text={
                },
                icon= {
                    Icon(
                        modifier = Modifier
                            .size(
                                if (scaffoldState.bottomSheetState.isCollapsed
                                ) 40.dp else 0.dp
                            )
                            .padding(2.dp)

                            ,
                        painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_24),
                        contentDescription = "ff"
                    )
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


    )
    {
        BottomSheetScaffold(
            // Defaults to BottomSheetScaffoldDefaults.SheetPeekHeight
            sheetPeekHeight = 0.dp,

            // Defaults to true
            sheetGesturesEnabled = true,

            scaffoldState = scaffoldState,

            sheetContent = {
                // Sheet content
                Column(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp)
                ) {

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
        ) {
            // Screen content
            Box(
                 modifier = Modifier.fillMaxSize()
            ) {
                var scrollState= rememberScrollState()
                Column(
                    modifier= Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp)
                        .verticalScroll(scrollState),

                ) {
                    Column(
                        modifier=Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(book.bookImage),
                            contentDescription = null,
                            modifier = Modifier
                                .size(300.dp)
                                .padding(0.dp, 4.dp)
                        )
                        Text(
                            style= MaterialTheme.typography.h6,
                            text = book.title
                        )
                        Text(
                            textAlign = TextAlign.Center,
                            text = "   " +book.description
                        )
                    }

                    Divider()
                    Text(
                        style=MaterialTheme.typography.h6,
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        text = "Author :"
                    )
                    val padding=20
                    chips(string = book.author , padding = padding)

                    Divider()
                    Text(
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        style=MaterialTheme.typography.h6,
                        text = "Contributors :"
                    )

                    chips(string = book.contributor , padding = padding)


                    Divider()
                    Text(
                        modifier = Modifier.padding(5.dp,10.dp,0.dp,5.dp),
                        style=MaterialTheme.typography.h6,
                        text = "Publisher :"
                    )
                    chips(string = book.publisher, padding = padding)
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
        .clickable {

        }
    ){
        Text(
            modifier=Modifier.padding(5.dp),
            text = string
        )
    }
}
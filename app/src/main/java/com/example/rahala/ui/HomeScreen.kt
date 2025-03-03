package com.example.rahala.ui

import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.draw.shadow

import androidx.compose.foundation.*
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rahala.R
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll

import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable


data class Category(val name: String, val icon: Painter)
data class Service(val name: String, val icon: Painter)


@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp), // Added vertical padding
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "See All",
                fontSize = 14.sp,
                color = Color(0xFF2E4157),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }
        Spacer(modifier = Modifier.height(8.dp)) // Ensures spacing from carousel
    }
}


@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()) // Ajout du scroll vertical
        )  {
            HeaderSection()
            SearchBar()

            val categories = listOf(
                Category("Mountains", painterResource(id = R.drawable.mountain)),
                Category("Beach", painterResource(id = R.drawable.beach)),
                Category("Lakes", painterResource(id = R.drawable.lake)),
                Category("Camp", painterResource(id = R.drawable.camp))
            )

            val services = listOf(
                Service("Hotel", painterResource(id = R.drawable.hotel)),
                Service("Flight", painterResource(id = R.drawable.flight)),
                Service("Bus", painterResource(id = R.drawable.bus)),
                Service("Boat", painterResource(id = R.drawable.boat))
            )

            Column {
                SectionHeader("Categories") { /* Action See All Categories */ }
                CategoryCarousel(categories, modifier = Modifier.padding(horizontal = 24.dp))

                Spacer(modifier = Modifier.height(26.dp))

                SectionHeader("Most Visited") { /* Action See All Most Visited */ }
                MostVisitedSection()

                Spacer(modifier = Modifier.height(16.dp))

                SectionHeader("Services") { /* Action See All Services */ }
                ServiceCarousel(services, modifier = Modifier.padding(horizontal = 26.dp))
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center
    ) {
        // Image en arrière-plan
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenu superposé (Logo + Texte)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logowhite),
                contentDescription = "App Logo",
                modifier = Modifier.size(180.dp)
            )
            //Text("Explore The World With US !", fontSize = 12.sp, color = Color.White)
        }
    }
}


@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-20).dp) // Permet de chevaucher les deux parties
            .padding(horizontal = 16.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(50)) // Ajoute une ombre en bas
            .background(Color.White, shape = RoundedCornerShape(50))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Outlined.Search, contentDescription = "Search Icon", tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text("Search any places...", color = Color.Gray, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )
        }
    }
}


@Composable
fun CategoryItem(category: Category, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(if (isSelected) Color(0xFF2E4157) else Color.White)
    val iconColor by animateColorAsState(if (isSelected) Color.White else Color(0xFF2E4157))
    val textColor by animateColorAsState(if (isSelected) Color.White else Color(0xFF2E4157))

    val fixedWidth = 96 // Largeur fixe en dp pour garantir l'affichage de 8 lettres

    Column(
        modifier = Modifier
            .width(fixedWidth.dp) // Applique une largeur fixe
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Icon(
                painter = category.icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(36.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = category.name,
                color = textColor,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ServiceItem(service: Service, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(if (isSelected) Color(0xFF2E4157) else Color.White)
    val iconColor by animateColorAsState(if (isSelected) Color.White else Color(0xFF2E4157))
    val textColor by animateColorAsState(if (isSelected) Color.White else Color(0xFF2E4157))

    val fixedWidth = 96 // Largeur fixe en dp

    Column(
        modifier = Modifier
            .width(fixedWidth.dp) // Largeur uniforme
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Icon(
                painter = service.icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(36.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = service.name,
                color = textColor,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CategoryCarousel(categories: List<Category>, modifier: Modifier = Modifier) {
    var selectedCategory by remember { mutableStateOf(categories.firstOrNull()) }
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier // Utilisation du modifier ici
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            CategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onClick = { selectedCategory = category }
            )
        }
    }
}

@Composable
fun ServiceCarousel(services: List<Service>, modifier: Modifier = Modifier) {
    var selectedService by remember { mutableStateOf(services.firstOrNull()) }
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier // Utilisation du modifier ici
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        services.forEach { service ->
            ServiceItem(
                service = service,
                isSelected = service == selectedService,
                onClick = { selectedService = service }
            )
        }
    }
}



/*@Composable
fun CategoriesSection() {
    Text("Categories", modifier = Modifier.padding(16.dp), fontSize = 18.sp)
    Row(modifier = Modifier.padding(8.dp)) {
        CategoryItem("Mountains", R.drawable.mountain)
        CategoryItem("Beach", R.drawable.beach)
        CategoryItem("Lakes", R.drawable.lake)
        CategoryItem("Camp", R.drawable.camp)
    }
}*/



@Composable
fun MostVisitedSection() {
    //Text("Most Visited", modifier = Modifier.padding(12.dp), fontSize = 18.sp,fontWeight = FontWeight.Bold)
    LazyRow(modifier = Modifier.padding(4.dp)) {
        items(listOf(
            PlaceData("Béjaia", "Algeria", 4.7, R.drawable.bejaia),
            PlaceData("Alger", "Algeria", 4.5, R.drawable.alger)
        )) { place ->
            PlaceItem(place.name, place.location, place.rating, place.imageRes)
        }
    }
}

data class PlaceData(val name: String, val location: String, val rating: Double, val imageRes: Int)

@Composable
fun PlaceItem(name: String, location: String, rating: Double, imageRes: Int) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp).width(250.dp)
    ) {
        Box(modifier = Modifier.height(230.dp)) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Text(
                        text = "★ $rating",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(12.dp)).padding(5.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = location,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}




/*@Composable
fun ServicesSection() {
    Text("Services", modifier = Modifier.padding(16.dp), fontSize = 18.sp)
    Row(modifier = Modifier.padding(8.dp)) {
        ServiceItem("Hotel", R.drawable.hotel)
        ServiceItem("Flight", R.drawable.flight)
        ServiceItem("Bus", R.drawable.bus)
        ServiceItem("Boat", R.drawable.boat)
    }
}*/



@Composable
fun BottomNavigationBar() {
    var selectedIndex by remember { mutableStateOf(0) }

    BottomNavigation(backgroundColor = Color.White) {
        val items = listOf(
            Triple(R.drawable.home, R.drawable.home_filled, "Home"),
            Triple(R.drawable.save, R.drawable.save_filled, "Save"),
            Triple(R.drawable.notification, R.drawable.notification_filled, "Home"),
            Triple(R.drawable.chat, R.drawable.chat_filled, "Home")
        )

        items.forEachIndexed { index, (outlinedIcon, filledIcon, description) ->
            val isSelected = selectedIndex == index
            val iconRes = if (isSelected) filledIcon else outlinedIcon
            val iconColor by animateColorAsState(if (isSelected) Color(0xFF2E4157) else Color.Gray)

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = description,
                        modifier = Modifier.size(25.dp),
                        tint = iconColor
                    )
                },
                selected = isSelected,
                onClick = { selectedIndex = index }
            )
        }
    }
}

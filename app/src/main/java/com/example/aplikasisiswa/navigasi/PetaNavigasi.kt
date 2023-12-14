import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplikasisiswa.R
import com.example.aplikasisiswa.uii.DestinasiEntry
import com.example.aplikasisiswa.uii.DestinasiHome
import com.example.aplikasisiswa.uii.DetailScreen
import com.example.aplikasisiswa.uii.DetailsDestination
import com.example.aplikasisiswa.uii.EntrySiswaScreen
import com.example.aplikasisiswa.uii.HomeScreen
import com.example.aplikasisiswa.uii.ItemEditDestination
import com.example.aplikasisiswa.uii.ItemEditSecreen

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(title = { Text(title)},
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        })
}

@Composable
fun HostNavigasi(
    navController: NavController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = DestinasiHome.route,
        modifier = Modifier)
    {
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = {
                    navController.navigate("${DetailsDestination.route}/$it")
                },
            )
        }
        composable(DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = {navController.popBackStack()}
                )
        }
        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.siswaIdArg){
                type = NavType.IntType
            })
        ){
            DetailScreen(
                navigateBack = {navController.popBackStack()},
                navigateToEditItem = {
                    navController.navigate("${ItemEditDestination.route}/$it")
                }
            )
        }

        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg){
                type = NavType.IntType
            })
        ){
            ItemEditSecreen(
                navigateBack = {navController.popBackStack()},
                onNavigateUp = { navController.navigateUp()})
        }

    }
}
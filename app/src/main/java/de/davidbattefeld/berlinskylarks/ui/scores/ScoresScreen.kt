package de.davidbattefeld.berlinskylarks.ui.scores

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.berlinskylarks.classes.viewmodels.ScoresViewModel
import de.davidbattefeld.berlinskylarks.enums.ViewState
import de.davidbattefeld.berlinskylarks.global.cardPadding
import de.davidbattefeld.berlinskylarks.ui.theme.BerlinSkylarksTheme
import de.davidbattefeld.berlinskylarks.ui.utility.ContentNotFoundView
import de.davidbattefeld.berlinskylarks.ui.utility.LoadingView

@Composable
fun ScoresScreen(
    modifier: Modifier = Modifier,
    detailRoute: (Int) -> Unit,
    setFabOnClick: (() -> Unit) -> Unit,
) {
    val vm: ScoresViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    var showExternalGames by rememberSaveable { mutableStateOf(true) }
    val tabTitles = listOf("Previous", "Current", "Next", "Any")

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 350.dp)
    ) {
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            TabRow(selectedTabIndex = vm.tabState) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = vm.tabState == index,
                        onClick = {
                            vm.tabState = index
                            vm.load()
                        },
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        }
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            Card(
                modifier = Modifier
                    .padding(cardPadding),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.8F)
                    ) {
                        Text(
                            text = "Show External Games",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Controls whether the list is filtered by just Skylarks games.",
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1.0F))
                    Switch(
                        modifier = Modifier.semantics {
                            contentDescription = "Show external Games"
                        },
                        checked = showExternalGames,
                        onCheckedChange = { showExternalGames = it },
                        colors = SwitchDefaults.colors(

                        )
                    )
                }
            }
        }
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))
        }
        when (vm.viewState) {
            ViewState.NoResults, ViewState.NotInitialised -> {
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    ContentNotFoundView("games")
                }
            }
            ViewState.Loading -> {
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    LoadingView()
                }
            }
            ViewState.Found -> {
                items(if (showExternalGames) vm.games else vm.skylarksGames) { game ->
                    ScoresItem(
                        game = game,
                        modifier = Modifier
                            .clickable {
                                detailRoute(game.id)
                            }
                    )
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        setFabOnClick { vm.load() }

        if (vm.games.isEmpty()) {
            vm.load()
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 400,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(
    showBackground = true,
    widthDp = 400
)
@Composable
fun ScoresScreenPreview() {
    BerlinSkylarksTheme {
       // ScoresScreen()
    }
}
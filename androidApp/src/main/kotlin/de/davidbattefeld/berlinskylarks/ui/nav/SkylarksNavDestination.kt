package de.davidbattefeld.berlinskylarks.ui.nav

import android.R.attr.data
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Scoreboard
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material.icons.outlined.TableRows
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class SkylarksNavDestination(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object Home : SkylarksNavDestination(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Stars
    )

    data object Scores : SkylarksNavDestination(
        route = "scores",
        title = "Scores",
        icon = Icons.Outlined.Scoreboard
    )

    data object ScoresDetail : SkylarksNavDestination(
        route = "scores_detail",
        title = "Game Detail",
        icon = Icons.Outlined.Scoreboard
    ) {
        const val scoreArg = "game_id"
        val routeWithArgs = "${route}/{${scoreArg}}"
        val arguments = listOf(
            navArgument(scoreArg) { type = NavType.IntType }
        )
    }

    data object Standings : SkylarksNavDestination(
        route = "standings",
        title = "Standings",
        icon = Icons.Outlined.TableRows
    )

    data object StandingsDetail : SkylarksNavDestination(
        route = "standings_detail",
        title = "Table Detail",
        icon = Icons.Outlined.TableRows
    ) {
        const val tableArg = "league_group_id"
        val routeWithArgs = "${route}/{${tableArg}}"
        val arguments = listOf(
            navArgument(tableArg) { type = NavType.IntType }
        )
    }

    data object Club : SkylarksNavDestination(
        route = "club",
        title = "Club",
        icon = Icons.Outlined.Shield,
    )

    data object Teams : SkylarksNavDestination(
        route = "club_teams",
        title = "Teams",
        icon = Icons.Outlined.Groups
    )

    data object TeamDetail : SkylarksNavDestination(
        route = "club_player_list",
        title = "Team Detail",
        icon = Icons.Outlined.Groups
    ) {
        const val teamArg = "team_id"
        val routeWithArgs = "${route}/{${teamArg}}"
        val arguments = listOf(
            navArgument(teamArg) { type = NavType.IntType }
        )
    }

    data object PlayerDetail : SkylarksNavDestination(
        route = "club_player_detail",
        title = "Player Detail",
        icon = Icons.Outlined.Person
    ) {
        const val playerArg = "player_id"
        val routeWithArgs = "${route}/{${playerArg}}"
        val arguments = listOf(
            navArgument(playerArg) { type = NavType.IntType }
        )
    }

    data object Functionary : SkylarksNavDestination(
        route = "functionary",
        title = "Functionary",
        icon = Icons.Outlined.Person
    )

    data object Settings : SkylarksNavDestination(
        route = "settings",
        title = "Settings",
        icon = Icons.Outlined.Settings
    )

    data object Info : SkylarksNavDestination(
        route = "settings_info",
        title = "App Info",
        icon = Icons.Filled.Info
    )

    data object Privacy : SkylarksNavDestination(
        route = "settings_privacy",
        title = "Privacy Policy",
        icon = Icons.Filled.Policy
    )

    data object LegalNotice : SkylarksNavDestination(
        route = "settings_legal",
        title = "Legal Notice",
        icon = Icons.Filled.Gavel
    )
}
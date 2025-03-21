package de.davidbattefeld.berlinskylarks.testdata

import de.davidbattefeld.berlinskylarks.global.BOGUS_ID
import de.davidbattefeld.berlinskylarks.model.Game
import de.davidbattefeld.berlinskylarks.model.League
import de.davidbattefeld.berlinskylarks.model.LeagueGroup
import de.davidbattefeld.berlinskylarks.model.LeagueTable

val testEntry = Game.LeagueEntry(
    team = Game.Team(name = "Test Team")
)

val testLeague = League(
    id = BOGUS_ID,
    name = "Kreisliga",
    season = 2099,
    classification = "Kreisliga",
    sport = "Baseball",
    acronym = "KRL",
    age_group = "Erwachsene"
)

val testLeagueGroup = LeagueGroup(
    id = BOGUS_ID,
    name = "All Leagues",
    acronym = "KBD",
    season = 2099,
    league = testLeague
)

val testGame = Game(
    id = BOGUS_ID,
    match_id = "12345",
    planned_innings = 7,
    league_id = 5555,
    away_runs = 5,
    home_runs = 7,
    away_team_name = "Road Team",
    home_team_name = "Home Team",
    human_state = "in limbo",
    time = "2022-45-67 12:00",
    home_league_entry = testEntry,
    away_league_entry = testEntry,
    scoresheet_url = null,
    league = testLeague,
    field = null,
    scorer_assignments = listOf(),
    umpire_assignments = listOf(),
)

val testRow = LeagueTable.Row(
    rank = "1.",
    team_name = "Puppeteers",
    short_team_name = "PUP",
    match_count = 7,
    wins_count = 5.0,
    losses_count = 2.0,
    quota = ".500",
    games_behind = "2",
    streak = "W2"
)

val testRow2 = LeagueTable.Row(
    rank = "1.",
    team_name = "Skylarks",
    short_team_name = "SKY",
    match_count = 7,
    wins_count = 5.0,
    losses_count = 2.0,
    quota = ".500",
    games_behind = "2",
    streak = "W2"
)

val testTable = LeagueTable(
    league_id = BOGUS_ID,
    league_name = "Kreisliga Baseball",
    season = 1970,
    rows = listOf(testRow, testRow, testRow2)
)
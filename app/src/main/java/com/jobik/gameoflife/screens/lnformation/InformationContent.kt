package com.jobik.gameoflife.screens.lnformation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jobik.gameoflife.R
import com.jobik.gameoflife.services.rate.RateService
import com.jobik.gameoflife.ui.helpers.BottomWindowInsetsSpacer
import com.jobik.gameoflife.ui.helpers.WindowWidthSizeClass
import com.jobik.gameoflife.ui.helpers.bottomWindowInsetsPadding
import com.jobik.gameoflife.ui.helpers.currentWidthSizeClass

@Composable
fun InformationContent() {
    val uriHandler = LocalUriHandler.current
    val currentWidthSize = currentWidthSizeClass()
    val countColumns = when (currentWidthSize) {
        WindowWidthSizeClass.Compact -> 1

        WindowWidthSizeClass.Medium -> 2

        WindowWidthSizeClass.Expanded -> 3
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(countColumns),
        contentPadding = PaddingValues(20.dp),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            val johnConwayUri = stringResource(id = R.string.JohnHortonConway_wiki_uri)
            LargeInformationCard(
                modifier = Modifier.animateItem(),
                image = R.drawable.john_horton_conway_poster,
                title = R.string.JohnHortonConway,
                body = R.string.JohnHortonConway_card_description,
                button = CardButton(
                    text = R.string.open_in_wikipedia,
                    onClick = { uriHandler.openUri(johnConwayUri) }
                )
            )
        }
        item {
            val gameOfLifeUri = stringResource(id = R.string.GameOfLife_wiki_uri)
            LargeInformationCard(
                modifier = Modifier.animateItem(),
                image = R.drawable.game_of_life_poster,
                title = R.string.the_game_of_life,
                body = R.string.GameOfLife_large_description,
                imageWithTint = true,
                button = CardButton(
                    text = R.string.open_in_wikipedia,
                    onClick = { uriHandler.openUri(gameOfLifeUri) }
                ),
            )
        }
        item {
            val context = LocalContext.current
            LargeInformationCard(
                modifier = Modifier.animateItem(),
                image = R.drawable.three_stars_v2,
                title = R.string.rate_dialog_title,
                body = R.string.rate_dialog_description,
                imageWithTint = true,
                button = CardButton(
                    text = R.string.rate,
                    onClick = {
                        RateService(context).openAppRating()
                    }
                ),
            )
        }
        item {
            val telegramGroupUri = stringResource(id = R.string.telegram_group_url)

            SmallInformationCard(
                modifier = Modifier.animateItem(),
                image = R.drawable.telegram_icon,
                title = R.string.telegram_community,
                body = R.string.telegram_community_description
            ) {
                uriHandler.openUri(telegramGroupUri)
            }
        }
        item {
            val githubRepositoryUri = stringResource(id = R.string.github_repository_url)

            SmallInformationCard(
                modifier = Modifier.animateItem(),
                image = R.drawable.github_icon,
                title = R.string.open_source_project,
                body = R.string.open_source_project_description
            ) {
                uriHandler.openUri(githubRepositoryUri)
            }
        }

        item { BottomWindowInsetsSpacer() }
    }
}
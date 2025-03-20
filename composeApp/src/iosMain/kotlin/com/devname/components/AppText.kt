package com.devname.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import jokersclutch.composeapp.generated.resources.Res
import jokersclutch.composeapp.generated.resources.pollerone_regular
import org.jetbrains.compose.resources.Font


@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 25.sp,
    fontWeight: FontWeight? = null,
    color: Color = Color.White,
    outlineColor: Color? = Color.Black,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Clip,
    lineHeight: TextUnit = TextUnit.Unspecified,
) {
    val fontFamily = FontFamily(Font(Res.font.pollerone_regular))
    Box(modifier) {
        if (outlineColor != null) {
            Text(
                text = text,
                modifier = Modifier.semantics { invisibleToUser() },
                color = outlineColor,
                style = LocalTextStyle.current.copy(drawStyle = Stroke(8f)),
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                lineHeight = lineHeight
            )
        }
        Text(
            modifier = modifier,
            text = text,
            fontSize = fontSize,
            color = color,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            textAlign = textAlign,
            overflow = overflow,
            lineHeight = lineHeight
        )
    }
}
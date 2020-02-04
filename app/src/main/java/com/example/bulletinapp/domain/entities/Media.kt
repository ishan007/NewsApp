package com.example.bulletinapp.domain.entities

import com.google.gson.annotations.SerializedName

data class Media(@SerializedName("media-metadata")val metadataList: List<MediaMetadata>)

data class MediaMetadata(val url: String)
package com.example.bulletinapp.viewmodel.events

/**
 * Event types
 */
sealed class EventType

object OnNewsItemSelected: EventType()

object OnLoadPageError : EventType()

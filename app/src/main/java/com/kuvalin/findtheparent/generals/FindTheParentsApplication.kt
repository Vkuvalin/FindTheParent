package com.kuvalin.findtheparent.generals

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.kuvalin.findtheparent.di.ApplicationComponent
import com.kuvalin.findtheparent.di.DaggerApplicationComponent

class FindTheParentsApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as FindTheParentsApplication).component
}
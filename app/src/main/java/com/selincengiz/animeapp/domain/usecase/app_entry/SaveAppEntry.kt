package com.selincengiz.animeapp.domain.usecase.app_entry

import com.selincengiz.animeapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}
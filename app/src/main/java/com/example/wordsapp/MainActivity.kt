/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.adapters.LetterAdapter
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayout: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView

        // Menu
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.layout_menu, menu)
                val changeLayoutButton: MenuItem = menu.findItem(R.id.action_switch_layout)
                changeLayout(changeLayoutButton, toggleLayout = false)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_switch_layout -> {
                        changeLayout(menuItem)
                        return true
                    }
                    else -> true
                }
            }
        })
    }

    // Changes the app layout from LinearLayout to GridLayout
    // and vice versa
    private fun changeLayout(menuItem: MenuItem?, toggleLayout: Boolean = true) {
        if (toggleLayout) isLinearLayout = !isLinearLayout

        if (isLinearLayout) {
            recyclerView.layoutManager = LinearLayoutManager(this)
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        }

        recyclerView.adapter = LetterAdapter()
    }

}

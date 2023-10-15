package de.atennert.gtk

import gtk.*
import kotlinx.cinterop.*

// https://docs.gtk.org/gtk3/getting_started.html

@OptIn(ExperimentalForeignApi::class)
fun activate(app: CPointer<GtkApplication>?, dataPointer: gpointer) {
    val data = dataPointer.asStableRef<String>().get()

    val window = gtk_application_window_new(app)
    gtk_window_set_title(window?.reinterpret(), "Hello $data")
    gtk_window_set_default_size(window?.reinterpret(), 200, 200)

    val buttonBox = gtk_button_box_new(GtkOrientation.GTK_ORIENTATION_HORIZONTAL)
    gtk_container_add(window?.reinterpret(), buttonBox)

    val button = gtk_button_new_with_label("Hello $data")
    g_signal_connect_data(
        button,
        "clicked",
        staticCFunction { dataPointer: gpointer ->
            val data = dataPointer.asStableRef<String>().get()
            println("Hello $data")
        }.reinterpret(),
        dataPointer,
        null,
        G_CONNECT_SWAPPED
    )
    g_signal_connect_data(
        button,
        "clicked",
        staticCFunction { widget: CPointer<GtkWidget> -> gtk_widget_destroy(widget) }.reinterpret(),
        window,
        null,
        G_CONNECT_SWAPPED
    )
    gtk_container_add(buttonBox?.reinterpret(), button)

    gtk_widget_show_all(window)
}

@OptIn(ExperimentalForeignApi::class)
fun main() {
    val app = gtk_application_new("de.atennert.gtk", G_APPLICATION_FLAGS_NONE)

    g_signal_connect_data(
        app,
        "activate",
        staticCFunction(::activate).reinterpret(),
        StableRef.create("KKON").asCPointer(),
        null,
        0.convert()
    )

    g_application_run(app?.reinterpret(), 0, null)

    g_object_unref(app)
}
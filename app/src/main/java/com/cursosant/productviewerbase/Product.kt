package com.cursosant.productviewerbase

/****
 * Project: Product Viewer
 * From: com.cursosant.productviewer
 * Created by Alain Nicolás Tello on 15/12/21 at 20:13
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
data class Product(var id: String? = null,
                   var name:String? = null,
                   var description: String? = null,
                   var imgUrl: String? = null,
                   var quantity: Int = 0,
                   var newQuantity: Int = 1,
                   var price: Double = 0.0,
                   var sellerId: String = ""){

    fun totalPrice(): Double = newQuantity * price

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}

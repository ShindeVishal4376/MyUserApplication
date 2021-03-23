package com.flourpicker.app.Room_Database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.flourpicker.app.Room_Database.AppDatabase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = AppDatabase.TABLE_NAME_ADDTOCART)
public class AddToCartInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId")
    private Integer userId;

    @ColumnInfo(name = "productEng")
    private String  productEng;

    @ColumnInfo(name = "productHin")
    private String productHin;

    @ColumnInfo(name = "productMar")
    private String productMar;

    @ColumnInfo(name = "discription")
    private String discription;

    @ColumnInfo(name = "discriptionHin")
    private String discriptionHin;

    @ColumnInfo(name = "discriptionMar")
    private String discriptionMar;

    @ColumnInfo(name = "imagePath")
    private String imagePath;

    @ColumnInfo(name = "productTypeId")
    private Integer productTypeId;

    @ColumnInfo(name = "productId")
    private Integer productId;

    @ColumnInfo(name = "mrp")
    private Double mrp;

    @ColumnInfo(name = "quantity")
    private Integer quantity;

    @ColumnInfo(name = "amount")
    private Double amount;

    @ColumnInfo(name = "size")
    private Integer size;

    @ColumnInfo(name = "grindingType")
    private Integer grindingType;

    @ColumnInfo(name = "offerPrice")
    private Double offerPrice;

    @ColumnInfo(name = "position")
    private Integer position;

    @ColumnInfo(name = "isInCart")
    private Boolean isInCart;

    @ColumnInfo(name = "isServiceAvailble")
    private Boolean isServiceAvailble;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductEng() {
        return productEng;
    }

    public void setProductEng(String productEng) {
        this.productEng = productEng;
    }

    public String getProductHin() {
        return productHin;
    }

    public void setProductHin(String productHin) {
        this.productHin = productHin;
    }

    public String getProductMar() {
        return productMar;
    }

    public void setProductMar(String productMar) {
        this.productMar = productMar;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDiscriptionHin() {
        return discriptionHin;
    }

    public void setDiscriptionHin(String discriptionHin) {
        this.discriptionHin = discriptionHin;
    }

    public String getDiscriptionMar() {
        return discriptionMar;
    }

    public void setDiscriptionMar(String discriptionMar) {
        this.discriptionMar = discriptionMar;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getGrindingType() {
        return grindingType;
    }

    public void setGrindingType(Integer grindingType) {
        this.grindingType = grindingType;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Boolean getInCart() {
        return isInCart;
    }

    public void setInCart(Boolean inCart) {
        isInCart = inCart;
    }

    public Boolean getServiceAvailble() {
        return isServiceAvailble;
    }

    public void setServiceAvailble(Boolean serviceAvailble) {
        isServiceAvailble = serviceAvailble;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

# ShoppingCart
Url request for listCart
http://localhost:9000/listCarts

Url addCart and Item
http://localhost:9000/saveCart
Json payload :
{
  "code": "001",
  "email": "test@gmail.com",
  "status": "COMPLETED",
  "items": [
    {
      "itemCode": "001",
      "itemName": "test",
      "category": "test",
      "price": "10000"
    }
  ]
}

Json Response:
{
  "code": "001",
  "email": "test@gmail.com",
  "status": "COMPLETED",
  "items": [
    {
      "itemCode": "001",
      "itemName": "test",
      "category": "test",
      "price": "10000"
    }
  ]
}

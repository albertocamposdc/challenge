
The project contains a simple API that saves a payment in an in-memory database (for the sake of this example lets use a in-memory-database).

1. Create a new endpoint to list all users that have a payment saved in the database (information about payments should be already filled).

End Point:
http://localhost:8080/api/clip/allUserPayment

response:
[
    {
        "id": "1",
        "name": "Julio Alberto"
    }
]

2. Create a new  endpoint so the api can support a disbursement process:
    - A disbursement process gets all transactions with status new and subtracts a fee of 3.5%  per transaction.
    - It updates all transactions with a status NEW  to PROCESSED.
    - Returns a list of users and the amount the'll get 
    -- Example - User_1 payment: 100, Disbursement: User_1:97.5 (Discount the fee)

EndPoint
http://localhost:8080/api/clip/disbursementProcess
response:
[
    {
        "id": 0,
        "payment": 1000.00,
        "userId": "1",
        "status": null,
        "disbursement": 965.000
    },
    {
        "id": 0,
        "payment": 1000.00,
        "userId": "1",
        "status": null,
        "disbursement": 965.000
    },
    {
        "id": 0,
        "payment": 1000.00,
        "userId": "1",
        "status": null,
        "disbursement": 965.000
    }
]
3. Create an endpoint that returns a report per user:
    - Report:
    `{
      user_name - user name
      payments_sum: - sum of all payments (no mater what's the status)
      new_payments: sum of all new payments 
      new_payments_amount: sum of the amount of each payment
    }`
EndPoint:
http://localhost:8080/api/clip/report
Request:
{
    "userId":1
}

Response:
{
    "userName": "Julio Alberto",
    "paymentsSum": 4,
    "newPayments": 1,
    "newPaymentsAmount": 1000.00
}

##Notes:
 

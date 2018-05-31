# transferAPI

# com.server.server class is a standlaone Main class. To start the server.
# Following REST endpoints are:
- /account/transfer     POST
  {
    toAccountNo : "5634897",
    fromAccountNo : "7862692",
    toSortCode : "20-34-23",
    fromSortCode : "67-34-23",
    amount : 234.56
  }

- /account/balance/{67-34-23}/5674234 GET
- /account/add/{67-34-23}/3213234 PUT

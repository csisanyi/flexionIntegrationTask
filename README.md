www.flexionmobile.comTable of contents
Instructions3
Testing3
Submission3
Buy Item4
Description4
Request Parameters4
Successful response4
Error response4
Get All Purchases5
Description5
Request Parameters5
Successful response5
Error response5
Consume Purchase6
Description6
Request Parameters6
Successful response6
Error response6
Confidential | Page 21. Instructions
A new game called Fun Flowers is topping the download charts and the game developer is now
looking to distribute their game outside of the Google Play Appstore to increase their revenue.
The game developer has therefore contacted Flexion, who have specialised in billing outside of
the Google Play Appstore, to provide them with the best possible billing coverage.
Since Fun Flowers can be distributed into multiple different game stores the developers have
decided to create an interface called Integration. Their interface allows them to nicely separate
the billing logic from their game code. Your mission is to use this interface to implement a billing
Integration that connects to the Flexion platform.
A jar file containing the interface for an Integration can be downloaded here:
https://recruitment-task.flexionmobile.com/javachallenge/pluginapi.jar
The methods that your Integration should support are “buy”, “consume” and “getPurchases”.
Buy should perform a purchase, consume should set the status of a purchase to “consumed”
and getPurchases should return a history of previous purchases and their consumption
statuses.
Your integration should connect to the following REST API to carry out the requests:
https://recruitment-task.flexionmobile.com/javachallenge/rest
The specification for this API can be found below.
2. Testing
We have provided an IntegrationTestRunner class in the zipped package. It accepts an
Integration which it will run a few select test cases on. You can use the IntegrationTestRunner
to test your implementation before you submit it to Flexion.
3. Submission
Please archive the solution and send it back as a reply to this email.
Confidential | Page 3API Specification
Buy Item
DESCRIPTION
Call this method to buy one item. Items can be bought multiple times. A JSON representation of
a Purchase will be returned if the call is successful.
buy
MethodPOST
URLhttps://recruitment-task.flexionmobile.com/javachallenge/rest/
developer/{developerId}/buy/{itemId}
REQUEST PARAMETERS
NameData TypeDescription
developerIdStringYour name
itemIdStringId of an item to buy
SUCCESSFUL RESPONSE
Response code
200
{
“consumed”: “false”,
“id”: "37536e7a-4e95-4862-89b5-c16e60b1f7d4",
“itemId”: "item1"
}
ERROR RESPONSE
Response code
404
That’s not right
Confidential | Page 4Get All Purchases
DESCRIPTION
Call this method to retrieve all previous purchases. Returns a list of Purchases in JSON format.
getPurchases
MethodGET
URLhttps://recruitment-task.flexionmobile.com/javachallenge/rest
/developer/{developerId}/all
REQUEST PARAMETERS
NameData TypeDescription
developerIdStringYour name
SUCCESSFUL RESPONSE
Response code
200
{
“purchases”: [
{
“consumed”: "false",
“id”: "d408199f-ae57-44a5-80a3-1ab7aeab9a20",
“itemId”: "item1"
},
{
“consumed”: "false",
“id”: "6a78bdac-23b5-4a92-94ec-bd037c8c2ad9",
“itemId”: "item2"
}
]
}
ERROR RESPONSE
Response code
404
That’s not right
Confidential | Page 5Consume Purchase
DESCRIPTION
Call this method to consume one purchase.
consume
MethodPOST
URLhttps://recruitment-task.flexionmobile.com/javachallenge/rest/
developer/{developerId}/consume/{purchaseId}
REQUEST PARAMETERS
NameData TypeDescription
developerIdStringYour name
purchaseIdStringAn ID of an existing
Purchase
SUCCESSFUL RESPONSE
Response code
200
ERROR RESPONSE
Response code
404
That’s not right
Confidential | Page 6

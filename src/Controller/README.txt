Instructions and Features:

1. The main method is in the RestaurantSimulation class.

2. To run the program,  run RestaurantSimulation.java

3. Input is read from the events.txt with each line simulating an event. The format of the input is:
                        Employee | iD | Input Type | TableID/KitchenID | ACTION DATA
                   e.g: Manager | 1 | StockCheck | 1 | Print Stock
    a. Server
        (i) Taking an order
        Server | 1 | Order | 1 | Mark * Burger # Tomato: +1 % Cheese: -1 , Fries # Potato: +1, Drink # None: 0
            ACTION DATA:
                -> custName1 * Dish1 # Addition1: int % Addition2: int % ... % AdditionN: int,  Dish2 Dish1 # Addition1: int % Addition2: int % ... % AdditionN: int | custName2 * Dish1 # Addition1: int % Addition2: int % ... % AdditionN: int,  Dish2 Dish1 # Addition1: int % Addition2: int % ... % AdditionN: int
                ***This format is very important as the algorithms rely on precise delimitation.

        (ii) Confirming orders and resubmitting an order
        Server | 1 | Confirmation | 1 | Mark * Burger-ACC, Fries-REJ, Drink-ACC + | Jason * Hot Dog-REJ, Boiled Eggs-REJ, Drink-ACC
            ACTION DATA:
                -> DEC = decision by customer which can be either accept (ACC) or reject (REJ)
                -> custName1 * Dish1-DEC, Dish2-dec,...DishN-DEC | custName2 * Dish1-DEC, Dish2-dec,...DishN-DEC
                -***Again, this format is very important as the algorithms rely on precise delimitation.

        (iii) Printing the Biil
        Server | 1 | Bill | 1 | Print Bill
            ACTION DATA:
            -> Enter: Print Bill

    b. Cook
        (i) Order seen in Kitchen
        Cook | 1 | KitchenStatus | 1 | Order, 1 | Received
        ACTION DATA:
            -> Enter: Received

        (ii)  Order ready for delivery
        Cook | 1 | OrderStatus | 1 | Order, 1 | Ready
            ACTION DATA:
             -> Enter: Ready

    c. Manager
        (i) Check Inventory
        Manager | 1 | StockCheck | 1 | Print Stock
            ACTION DATA:
            -> Enter: Print Stock

        (ii) Receiving the Shipment
        Manager | 1 | Shipment | 1 | Received | Bread: 10, Lettuce: 10, Cheese: 10, Tomato: 10, Sausage: 10, Potato: 10
            ACTION DATA:
            -> Ingredient1: int, Ingredient2: int, Ingredient3: int, ..., IngredientN: int


4. Output:
Each time a server, cook or manager does something e.g check inventory, confirmed delivery,
there is a printout to the screen e.g:
                        Kitchen Status of Order #1:
                        Order #1 READY for delivery

The output is demonstrated solely by examples.

   a. Server
    (i) Input = Order
        Output:
        | BEGIN--- Order #1 ---BEGIN |
        Customer: Mark
        Name of dish: Burger Extras: (Cheese: -1) (Tomato: 1)
        Name of dish: Fries Extras: (Potato: 1)
        Name of dish: Drink Extras: (Pop Can: 1)
        | END--- Order #1 ---END |

     (ii) Input = Delivery
        Output
        A. Some customer rejected the delivery and server resubmits order

        Order #1 - REDO returned Dishes.
        | BEGIN--- Order #1 ---BEGIN |
        Customer: Mark
        Name of dish: Fries Extras: (Potato: 1)
        | END--- Order #1 ---END |

        B. All customers have accepted their orders:
        Order #1 - COMPLETE.

      (iii) Input = Bill
        Output:
        Bill for Table #1
        Name of dish: Burger Extras: (Cheese: -1) (Tomato: 1) $5.5
        Name of dish: Drink Extras: (Pop Can: 1) $1.5
        Name of dish: Drink Extras: (Pop Can: 1) $1.5
        Total Price: $8.5

    b. Cook
        (i) Input = KitchenStatus
            Output:
            Order #1 SEEN by Cook1

        ii) Input = OrderStatus
            Output
            A. Some customer rejected the delivery and server resubmits order

            Order #1 - REDO returned Dishes.
            | BEGIN--- Order #1 ---BEGIN |
            Customer: Mark
            Name of dish: Fries Extras: (Potato: 1)
            | END--- Order #1 ---END |

            B. All customers have accepted their orders:
            Order #1 - COMPLETE.

    c. Manager
        (i) Input = StockCheck
        Output:
        Current Stock
        Potato 10
        Cream Scoop 10
        Lettuce 10
        Sausage 10
        Cone 10

        (ii) Input = Shipment
        Output:
        Current Stock
        Potato 4
        Cream Scoop 6
        Lettuce 9
        Sausage 8

        Stock after shipment received:


        Current Stock
        Potato 14
        Cream Scoop 16
        Lettuce 19
        Sausage 18

    c. Inventory request email text
        Output:
        This is written to the file request.txt by the Kitchen.checkThreshold

5. Other features
This program only handles 1 cook, 1 server, 1 table, 1 manager. The manager is also the receiver.

6. Unused classes.
Some classes like Log.java and methods maybe unused now, but will be applied in phase2.

7. Summary of events:

    a. Server takes order (input)
    -updates order list for each customer.
    -updates order queue.
    -prints to the screen.

    b. Cook acknowledges order (input)
    -prints to screen

    c. Cook confirms order ready (input)
    -prints to screen

    d. Ingredients deducted from inventory
    -prints to screen

    e. Server confirms customer accepted dish(input)
    -prints to screen
    -adds price to bill
    -prints bill

    f. Server alerts customer rejected dish
    -recreates order
    -prints to screen

    g. Manager checks inventory of ingredients (input)
    -prints to screen

    h. Manager accepts new shipments
    -prints new stock levels

    i. Stock to be ordered
    -Written to order.txt which appears in the phase1 directory each time program is run
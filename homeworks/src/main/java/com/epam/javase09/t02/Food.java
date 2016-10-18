package com.epam.javase09.t02;

public class Food {

    private Food(String name, String price, int id, String description, int calories) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.description = description;
        this.calories = calories;
    }

    private String name;
    private String price;
    private int id;
    private String description;
    private int calories;


    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    public static FoodBuilder getFoodBuilder(){
        return new FoodBuilder();
    }

    static class FoodBuilder{
        private String name;
        private String price;
        private int id;
        private String description;
        private int calories;


        public FoodBuilder setName(String name){
            this.name=name;
            return this;
        }

        public FoodBuilder setId(int id){
            this.id=id;
            return this;
        }

        public FoodBuilder setPrice(String price){
            this.price=price;
            return this;
        }

        public FoodBuilder setDescription(String description){
            this.description=description;
            return this;
        }

        public FoodBuilder setCalories(int calories){
            this.calories=calories;
            return this;
        }

        public Food build(){
            return new Food(name,price,id,description,calories);
        }
    }

    enum FOOD_PARAMETER {
        NAME{
            @Override
            void handle(FoodBuilder builder, String parameter) {
                builder.setName(parameter);
            };
        },
        /*FOOD{
                @Override
                void handle(FoodBuilder builder, String parameter) {
                    builder.setId(Integer.parseInt(parameter));
                };
        },*/
        PRICE {
            @Override
            void handle(FoodBuilder builder, String price) {
                builder.setPrice(price);
            }

            ;
        },
        DESCRIPTION {
            @Override
            void handle(FoodBuilder builder, String price) {
                builder.setName(price);
            }
        },
        CALORIES {
            @Override
            void handle(FoodBuilder builder, String price) {
                builder.setName(price);
            }
        },
        EMPTY{
            @Override
            void handle(FoodBuilder builder, String parameter) {
            }
        };

        public static FOOD_PARAMETER getFromString(String parameterString){
            try{
                return FOOD_PARAMETER.valueOf(parameterString.toUpperCase());
            }
            catch(Exception ex){
                return FOOD_PARAMETER.EMPTY;
            }
        }

        abstract void handle(FoodBuilder builder,String parameter);
    }
}

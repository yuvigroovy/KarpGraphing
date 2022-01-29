package com.example.karpgraphing;

/***************************************************************************
             _   __                   _____                 _     _             
            | | / /                  |  __ \               | |   (_)            
           | |/ /  __ _ _ __ _ __   | |  \/_ __ __ _ _ __ | |__  _ _ __   __ _ 
          |    \ / _` | '__| '_ \  | | __| '__/ _` | '_ \| '_ \| | '_ \ / _` |
         | |\  \ (_| | |  | |_) | | |_\ \ | | (_| | |_) | | | | | | | | (_| | 
        |_| \_/\__,_|_|  | .__/   \____/_|  \__,_| .__/|_| |_|_|_| |_|\__, |
                        | |                     | |                   __/ |
                        |_|                     |_|                  |___/ 

***************************************************************************/

public class Variable extends Node{
    char letter;
    public Variable(char letter){
        this.letter = letter;
    }

    char getLetter(){
        return letter;
    }

    public String toString(){
        return Character.toString(letter);
    }
}

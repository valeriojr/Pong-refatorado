package pong.gameObjects;

import java.util.*;

//Singleton Pattern

public class ObjectManager {
    private static ObjectManager instance = null;

    private Map<Integer, GameObject> objects;

    private ObjectManager(){
        objects = new TreeMap<>();
    }

    public static ObjectManager getInstance() {
        if(instance == null){
            instance = new ObjectManager();
        }
        return instance;
    }

    public Map<Integer, GameObject> getGameObjects() {
        return objects;
    }

    public GameObject getObject(int key){
        return objects.get(key);
    }

    public void add(int id, GameObject gameObject){
        objects.put(id, gameObject);
    }

    public Iterator<GameObject> findObjectsByType(GameObject.Type type){
        List<GameObject> filteredList = new ArrayList<>();

        for(GameObject gameObject : objects.values()){
            if(gameObject.getType() == type){
                filteredList.add(gameObject);
            }
        }

        return filteredList.iterator();
    }

}

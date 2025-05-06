import json 
import os 
import image
import tri2
from PIL import Image
import Erreur


def verifJson(path):
    try:
        with open(path,"r") as f:
            json.load(f)
            return True
    except json.JSONDecodeError :
        print("Json non valide")
        return False
    except:
        print("autre Erreur")
        return False


def verif_possible_ecrire(path):
    try :
        with open(path,'w') as f:
            f.write("")
            return True
    except PermissionError :
        print("Aucun droit d'écriture sur le fichier")
        return False

def creationJsonAvecAlbum(nom,album): 
    L = {}
    file = nom 
    files = os.listdir(os.path.join(os.getcwd(),"WorkingDirectory"))
    if album not in files :
        raise  Erreur.albumdoesnotexist("L'album choisi n'existe pas")
    path = os.path.join(os.path.join(os.getcwd(),"WorkingDirectory"),album, file)
    with open(path, "w") as f :
        json.dump(L,f,indent=2)

    
def continuer(img_path,Dico_etiquette,nom_album):#Changer fonction pour ne plus faire de cd et faire belek aux différents albums
    #if nom_album not in files :
     #   raise  Erreur.albumdoesnotexist("L'album choisi n'existe pas")

    chemin_relatif_travail = "demo/WorkingDirectory"
    if not VerifSiJsonExist(os.path.join(chemin_relatif_travail,nom_album)) :
        creationJsonAvecAlbum("data.json",nom_album)
    if image.VerifQueDesIMG(os.path.join(chemin,'images')) :
        files = os.listdir(chemin)
        creationJsonAvecAlbum("data_temp",nom_album)
        verif_possible_ecrire(chemin)
        with open(chemin,"r") as f :
            data = json.load(f)
        for img in files:   
            clef = hash(img)
            D_img={"nom" : img}
            D_img["album"]=nom_album
            for catégorie in Dico_etiquette.keys():
                D_img[catégorie] = tri2.tri_couleur(img) ##fonction de tri pour associer la valeur 
            data[clef] = D_img
        with open("data_temp.json","w") as f:
            json.dump(data,f)
        if verifJson(chemin_temp):
            os.remove(chemin)
            os.rename(chemin_temp,chemin)
        return True 
    return False

def json_in_dico(fic_json):
    with open(fic_json,"r") as f:
            data =json.load(f)
    return data

def dico_in_json(fic_json,dico):
    with open(fic_json,'w') as f:
        json.dump(dico,f)
    pass 
        
    

def VerifSiJsonExist(path_directory):   
    files = os.listdir(path_directory)
    if  "data.json" in files :
        return True
    return False


def renvoie_photo(catégorie,etiquette,nom_album):
    files = os.getcwd()
    if nom_album not in files :
        raise  Erreur.albumdoesnotexist("L'album choisi n'existe pas ")
    data = {}
    Img_Correct = []
    with open( "","r") as f :
        data = json.load(f)
    for i in data.keys() :
        if data[i][catégorie] == etiquette and data[i]["album"] == nom_album :
            Img_Correct.append(data[i]["nom"])
    return Img_Correct

D = { "Couleurs" : ["r","g","b"] }

print(os.path.join("demo","WorkingDirectory","TestNouvelAlbum"))
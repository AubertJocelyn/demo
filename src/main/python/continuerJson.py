import json 
import os 
import image
import module_de_tri
from PIL import Image
import Erreur
import sys 
import inspect
import hashlib
from Erreur import albumdoesnotexist
import module_de_tri.fonctionTri

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

def creationJsonAvecAlbum(nom,album,base_dir=None): #Fonction qui permet de créer un fichier Json vide dans un album précis

    base_dir = base_dir or os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd())))
    working_dir = os.path.join(base_dir, "WorkingDirectory")
    albums = os.listdir(working_dir)

    if album not in albums:
        raise albumdoesnotexist("L'album choisi n'existe pas")

    path = os.path.join(working_dir, album, nom)
    with open(path, "w") as f:
        json.dump({}, f, indent=2)



def continuer(nom_album): #Fonction qui permet d'écrire dans le fichier json d'un album apprès l'ajout de nouvelles photos, ou après création 
    #On récupère la liste des album existant et le chemin du projet
    demo_directory = os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd())))

    albums = os.listdir(os.path.join(demo_directory,"WorkingDirectory"))

    if nom_album not in albums :
        raise  Erreur.albumdoesnotexist("L'album choisi n'existe pas")
    
    chemin_album = os.path.join(demo_directory,"WorkingDirectory",nom_album)
    chemin = os.path.join(demo_directory,"WorkingDirectory",nom_album,"images")
    chemin_json = os.path.join(demo_directory, "WorkingDirectory", nom_album, "data.json")
    chemin_temp = os.path.join(demo_directory,"WorkingDirectory",nom_album,"data_temp.json")

    if not os.path.exists(chemin_json) :
        creationJsonAvecAlbum("data.json",nom_album)

    if image.VerifQueDesIMG(chemin) :
        
        files = os.listdir(chemin)
        creationJsonAvecAlbum("data_temp",nom_album)
        verif_possible_ecrire(chemin_temp)
        data = json_in_dico(chemin_json)
        for img in files:   
            clef_img = hashlib.sha256(img.encode()).hexdigest()
            if not is_img_in_Dico(data,clef_img):
                D_img={"nom" : img}
                D_img["album"]=nom_album
                chemin_image = os.path.join(demo_directory,"WorkingDirectory",nom_album,"images",img)
                applique_tout_tri(chemin_image,D_img)
                data[clef_img] = D_img
        dico_in_json(chemin_temp,data)
        
        if verifJson(chemin_temp):
            os.remove(chemin_json)
            os.rename(chemin_temp,chemin_json)
        return True 
    
    return False

def json_in_dico(fic_json):
    with open(fic_json,"r") as f:
            data =json.load(f)
    return data

def dico_in_json(fic_json,dico):
    with open(fic_json,'w') as f:
        json.dump(dico,f) 
        


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



def get_etiquettes():
    D={}
    """demo_directory = os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd())))"""
    demo_directory = os.getcwd()
    path_txt = os.path.join(demo_directory,"ListeCategoriesEtEtiquettes.txt")
    fic = open(path_txt,"r")
    mots = []
    for ligne in fic:
        mots.append(ligne.split())
    for i in mots :
        D[i[0]]=[]
        for j in range (1,len(i)):
            D[i[0]].append(i[j])
    return D

def applique_tout_tri(chemin_img,Dico_img):
    
    fonctions_tri = {
        nom: fonction
        for nom, fonction in inspect.getmembers(module_de_tri.fonctionTri, inspect.isfunction)
    }
    
    for nom_fonction, fonction in fonctions_tri.items():
        try:
            valeur = fonction(chemin_img)
            Dico_img[nom_fonction] = valeur
        except Exception as e:
            print(f"[ERREUR] {nom_fonction} a échoué sur {chemin_img} : {e}")
    
    return Dico_img

def is_img_in_Dico(Dico,clef_img):
    return clef_img in Dico.keys()



##Bloc de code permettant l'utilisation du code avec un argument 

argument1 = sys.argv[1]



def main():
    continuer(argument1)

main()



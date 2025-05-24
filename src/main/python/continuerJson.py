import json 
import os 
import image
import module_de_tri
from PIL import Image
import Erreur
import sys 
import inspect
import hashlib

import module_de_tri.fonctionTri

def verifJson(path): #Fonction qui permet de lever une exception si le json qu'on essaie de lire est corrompu
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


def verif_possible_ecrire(path): # Fonction de test qui permet de savoir si on a les droits d'écriture (qui a servi a régler des problèmes au début du projet)
    try :
        with open(path,'w') as f:
            f.write("")
            return True
    except PermissionError :
        print("Aucun droit d'écriture sur le fichier")
        return False

def creationJsonAvecAlbum(nom,album): #Fonction qui permet de créer un fichier Json vide dans un album précis
    L = {}
    file = nom 
    files = os.listdir(os.path.join(os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd()))),"WorkingDirectory"))
    if album not in files :
        raise  Erreur.albumdoesnotexist("L'album choisi n'existe pas")
    path = os.path.join(os.path.join(os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd()))),"WorkingDirectory"),album, file)
    with open(path, "w") as f :
        json.dump(L,f,indent=2)


def continuer(nom_album): #Fonction qui permet d'écrire dans le fichier json d'un album apprès l'ajout de nouvelles photos, ou après création 
    
    #On récupère la liste des album existant et le chemin du projet
    demo_directory = os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd())))
    albums = os.listdir(os.path.join(demo_directory,"WorkingDirectory"))

    #On lève une exception si l'album n'existe pas
    if nom_album not in albums :
        raise  Erreur.albumdoesnotexist("L'album choisi n'existe pas")
    
    #On récupère/créé tout les chemins dont on va avoir besoin 
    chemin_album = os.path.join(demo_directory,"WorkingDirectory",nom_album)
    chemin = os.path.join(demo_directory,"WorkingDirectory",nom_album,"images")
    chemin_json = os.path.join(demo_directory, "WorkingDirectory", nom_album, "data.json")
    chemin_temp = os.path.join(demo_directory,"WorkingDirectory",nom_album,"data_temp.json")

    #On vérifie si le Json existe, s'il n"existe pas on le créé
    if not os.path.exists(chemin_json) :
        creationJsonAvecAlbum("data.json",nom_album)

    #On vérifie ensuite si le fichier image ne contient que des images
    if image.VerifQueDesIMG(chemin) :
        
        #On vérifie si on peut écrire dans le json 
        files = os.listdir(chemin)
        creationJsonAvecAlbum("data_temp",nom_album)
        verif_possible_ecrire(chemin_temp)
        data = json_in_dico(chemin_json)

        #On parcourt toutes les images présentes dans le fichier
        for img in files:   
            clef_img = hashlib.sha256(img.encode()).hexdigest()
            
            #On génère le dictionnaire de chaque image, pour le ranger dans le dictionnaire associer a l'album, qu'on écrit dans un json temporaire 
            if not is_img_in_Dico(data,clef_img):
                D_img={"nom" : img}
                D_img["album"]=nom_album
                chemin_image = os.path.join(demo_directory,"WorkingDirectory",nom_album,"images",img)
                applique_tout_tri(chemin_image,D_img)
                data[clef_img] = D_img
        dico_in_json(chemin_temp,data)
        
        #Si le json est valide, on suprrime l'ancien et on le remplace par le temporaire
        if verifJson(chemin_temp):
            os.remove(chemin_json)
            os.rename(chemin_temp,chemin_json)
        return True 
    
    return False

def json_in_dico(fic_json): #Fonction qui permet renvoie un dictionnaire contenant le fihcier JSON
    with open(fic_json,"r") as f:
            data =json.load(f)
    return data

def dico_in_json(fic_json,dico):#Fonction qqui permet d'écrire un dictionnaire dans un json
    with open(fic_json,'w') as f:
        json.dump(dico,f) 
        


def renvoie_photo(catégorie,etiquette,nom_album): #Fonction qui permet de renvoyer les images qui ont une catégorie en particulier
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



def get_etiquettes():#Fonction qui permet d'obtenir la liste des étiquettes depuis le txt qui les contients
    D={}
    demo_directory = os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd())))
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

def applique_tout_tri(chemin_img,Dico_img): #Fonction qui permet d'appliquer tout les tris sur une image et de compléter le dictionnaire qui lui est associé
    
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

def is_img_in_Dico(Dico,clef_img): #Fonction permettant de savoir si une image est déja dans un dictionnaire
    return clef_img in Dico.keys()


##Bloc de code permettant l'utilisation du code avec un argument 

argument1 = sys.argv[1]

def main():
    continuer(argument1)

main()



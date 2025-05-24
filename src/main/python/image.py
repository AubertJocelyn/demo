import os 
from PIL import Image
import os.path

def verifsiimage(fic): #Permet  de verifier si un fichier est une image
    try:
        image= Image.open(fic)
        return True
    except :
        return False

def VerifQueDesIMG(directory): #Permet de verifier si un fichier ne contient que des images
    if os.path.isdir(directory):
        for fichier in os.listdir(directory) :
            chemin = os.path.join(directory, fichier)
            if not verifsiimage(chemin):
                return False
    return True 



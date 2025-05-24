import os
import json
import tempfile
import pytest
from unittest import mock
from continuerJson import (
    verifJson, verif_possible_ecrire, creationJsonAvecAlbum, json_in_dico,
    dico_in_json, is_img_in_Dico
)
import Erreur


#Pour faire les tests, mettre en commentaire le dernier bloc de code du fichier continuerJson.py

# --- verifJson ---
def test_verifJson_valide(tmp_path):
    path = tmp_path / "valide.json"
    path.write_text(json.dumps({"a": 1}))
    assert verifJson(str(path)) is True

def test_verifJson_invalide(tmp_path):
    path = tmp_path / "invalide.json"
    path.write_text("{ a: ")
    assert verifJson(str(path)) is False

def test_verifJson_fichier_absent():
    assert verifJson("fichier_inexistant.json") is False

# --- verif_possible_ecrire ---
def test_verif_possible_ecrire_possible(tmp_path):
    path = tmp_path / "test.txt"
    assert verif_possible_ecrire(str(path)) is True

def test_verif_possible_ecrire_permission(monkeypatch):
    def open_fail(*args, **kwargs):
        raise PermissionError
    monkeypatch.setattr("builtins.open", open_fail)
    assert verif_possible_ecrire("chemin/faux") is False

# --- creationJsonAvecAlbum ---
def test_creationJsonAvecAlbum_album_existe(tmp_path):
    album_dir = tmp_path / "WorkingDirectory" / "mon_album"
    album_dir.mkdir(parents=True)
    creationJsonAvecAlbum("data.json", "mon_album", base_dir=tmp_path)

    target_file = album_dir / "data.json"
    assert target_file.exists()

    with open(target_file) as f:
        contenu = json.load(f)
        assert contenu == {}

def test_creationJsonAvecAlbum_album_inexistant(tmp_path):
    (tmp_path / "WorkingDirectory").mkdir()
    with pytest.raises(Erreur.albumdoesnotexist):
        creationJsonAvecAlbum("data.json", "inexistant", base_dir=tmp_path)

# --- json_in_dico ---
def test_json_in_dico_valide(tmp_path):
    path = tmp_path / "data.json"
    contenu = {"clé": "valeur"}
    with open(path, "w") as f:
        json.dump(contenu, f)
    assert json_in_dico(str(path)) == contenu

# --- dico_in_json ---
def test_dico_in_json(tmp_path):
    path = tmp_path / "data.json"
    dico = {"a": 1}
    dico_in_json(str(path), dico)
    with open(path) as f:
        contenu = json.load(f)
    assert contenu == dico

# --- is_img_in_Dico ---
def test_is_img_in_Dico_vrai():
    dico = {"abc": {}}
    assert is_img_in_Dico(dico, "abc") is True

def test_is_img_in_Dico_faux():
    dico = {"xyz": {}}
    assert is_img_in_Dico(dico, "abc") is False

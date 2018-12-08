import pygame

class Sounds:
    def __init__(self):
        self.mainTrack = pygame.mixer.music.load("sounds/maintheme.mp3")  # theme melodie
        self.fireSound = pygame.mixer.Sound("sounds/hit.wav")  # sound of click
        self.fireSound.set_volume(0.5)
        self.popSound = pygame.mixer.Sound("sounds/pop.wav")  # sound for appearance of mole
        self.hurtSound = pygame.mixer.Sound("sounds/hurt.wav")  # sound of hit the mole
        self.levelSound = pygame.mixer.Sound("sounds/point.wav")  # sound for level up
        pygame.mixer.music.play(-1)

    def playPop(self):
        self.popSound.play()

    def stopPop(self):
        self.popSound.stop()

    def playHurt(self):
        self.hurtSound.play()

    def stopHurt(self):
        self.hurtSound.stop()

    def playLevelUp(self):
        self.levelSound.play()

    def stopLevelUp(self):
        self.levelSound.stop()

    def playFire(self):
        self.fireSound.play()

    def stopFire(self):
        self.fireSound.stop()
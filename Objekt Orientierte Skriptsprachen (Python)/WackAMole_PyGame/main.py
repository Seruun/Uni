from debugger import Debug
from sound import *

import pygame
import random
from pygame import *

class HitMe:
    def __init__(self):
        # Define constants
        self.SCREEN_Breite = 800
        self.SCREEN_Hoehe = 600
        self.FPS = 60
        self.MOLE_Breite = 90
        self.MOLE_Hoehe = 81
        self.FONT_SIZE = 31
        self.FONT_TOP_MARGIN = 26
        self.LEVEL_SCORE_GAP = 5  # every 5 successful hit the speed level will grow  to a new level
        self.LEFT_MOUSE_BUTTON = 1
        self.GAME_TITLE = "Hit me if You can"
        # Initialize player's score, number of missed hits and level
        self.score = 0
        self.misses = 0
        self.level = 1
        # Initialize screen
        self.screen = pygame.display.set_mode((self.SCREEN_Breite, self.SCREEN_Hoehe))
        pygame.display.set_caption(self.GAME_TITLE)
        self.background = pygame.image.load("images/bg.png")
        self.pic_num = random.randint(1, 10)
        # Font object for displaying text
        self.font_obj = pygame.font.Font('./fonts/Action_Man.ttf', self.FONT_SIZE)
        # Initialize the mole's sprite sheet
        # 6 different states
        # Positions of the holes in background
        self.hole_positions = []
        self.hole_positions.append((381, 295))
        self.hole_positions.append((119, 366))
        self.hole_positions.append((179, 169))
        self.hole_positions.append((404, 479))
        self.hole_positions.append((636, 366))
        self.hole_positions.append((658, 232))
        self.hole_positions.append((464, 119))
        self.hole_positions.append((95, 43))
        self.hole_positions.append((603, 11))
        # Init debugger
        self.debugger = Debug("debug")
        # Sound effects
        self.soundEffect = Sounds()

        # Calculate the player level according to his current score & the LEVEL_SCORE_GAP constant

    def get_player_level(self):
        newLevel = 1 + int(self.score / self.LEVEL_SCORE_GAP)
        if newLevel != self.level:
            # if player get a new level play this sound
            self.soundEffect.playLevelUp()
        return 1 + int(self.score / self.LEVEL_SCORE_GAP)

        # Get the new duration between the time the mole pop up and down the holes
        # It's in inverse ratio to the player's current level

    def get_interval_by_level(self, initial_interval):
        new_interval = initial_interval - self.level * 0.15
        if new_interval > 0:
            return new_interval  # new speed level
        else:
            return 0.05  # maximum speed level

        # Check whether the mouse click hit the mole or not

    def is_mole_hit(self, mouse_position, current_hole_position):
        mouse_x = mouse_position[0]
        mouse_y = mouse_position[1]
        current_hole_x = current_hole_position[0]
        current_hole_y = current_hole_position[1]
        if (mouse_x > current_hole_x) and (mouse_x < current_hole_x + self.MOLE_Breite) and (
                mouse_y > current_hole_y) and (mouse_y < current_hole_y + self.MOLE_Hoehe):
            return True
        else:
            return False

        # Update the game states, re-calculate the player's score, misses, level

    def update(self):
        # Update the player's score
        current_score_string = "SCORE: " + str(self.score)
        score_text = self.font_obj.render(current_score_string, True, (255, 255, 255))
        score_text_pos = score_text.get_rect()
        score_text_pos.centerx = self.SCREEN_Breite / 5 * 2
        score_text_pos.centery = self.FONT_TOP_MARGIN
        self.screen.blit(score_text, score_text_pos)
        # Update the player's misses
        current_misses_string = "MISSES: " + str(self.misses)
        misses_text = self.font_obj.render(current_misses_string, True, (255, 255, 255))
        misses_text_pos = misses_text.get_rect()
        misses_text_pos.centerx = self.SCREEN_Breite / 5 * 4
        misses_text_pos.centery = self.FONT_TOP_MARGIN
        self.screen.blit(misses_text, misses_text_pos)
        # Update the player's level
        current_level_string = "LEVEL: " + str(self.level)
        level_text = self.font_obj.render(current_level_string, True, (255, 255, 255))
        level_text_pos = level_text.get_rect()
        level_text_pos.centerx = self.SCREEN_Breite / 5 * 1
        level_text_pos.centery = self.FONT_TOP_MARGIN
        self.screen.blit(level_text, level_text_pos)

        # Start the game's main loop
        # Contains some logic for handling animations, mole hit events, etc..

    def start(self):
        cycle_time = 0
        num = -1
        loop = True
        is_down = False
        interval = 0.1
        initial_interval = 1
        frame_num = 0
        left = 0
        # Time control variables
        clock = pygame.time.Clock()

        while loop:
            for event in pygame.event.get():

                str1 = "images/Marvel"
                str2 = "Chibi.png"
                pic_name = str1 + str(self.pic_num) + str2
                self.sprite_sheet = pygame.image.load(pic_name)
                self.sprite_sheet = pygame.transform.scale(self.sprite_sheet, (80, 80))

                if event.type == pygame.QUIT:
                    loop = False
                if event.type == MOUSEBUTTONDOWN and event.button == self.LEFT_MOUSE_BUTTON:
                    self.soundEffect.playFire()
                    if self.is_mole_hit(mouse.get_pos(), self.hole_positions[frame_num]) and num > 0 and left == 0:
                        num = 3
                        left = 14
                        is_down = False
                        interval = 0
                        self.score += 1  # Increase player's score
                        self.level = self.get_player_level()  # Calculate player's level
                        # Stop popping sound effect
                        self.soundEffect.stopPop()
                        # Play hurt sound
                        self.soundEffect.playHurt()
                        self.pic_num = random.randint(1, 10)
                        self.update()
                    else:
                        self.misses += 1
                        self.update()

            if num > 5:
                self.screen.blit(self.background, (0, 0))
                self.update()
                num = -1
                left = 0

            if num == -1:
                self.screen.blit(self.background, (0, 0))
                self.update()
                num = 0
                is_down = False
                interval = 0.5
                frame_num = random.randint(0, 8)

            mil = clock.tick(self.FPS)
            sec = mil / 1000.0
            cycle_time += sec
            if cycle_time > interval:
                pic = self.sprite_sheet
                self.screen.blit(self.background, (0, 0))
                self.screen.blit(pic, (self.hole_positions[frame_num][0] - left, self.hole_positions[frame_num][1]))
                self.update()
                if is_down is False:
                    num += 1
                else:
                    num -= 1
                if num == 4:
                    interval = 0.3
                elif num == 3:
                    num -= 1
                    is_down = True
                    self.soundEffect.playPop()
                    interval = self.get_interval_by_level(initial_interval)  # get the newly decreased interval value
                else:
                    interval = 0.1
                cycle_time = 0
            # Update the display
            pygame.display.flip()

# Initialize the game
pygame.mixer.init(frequency=22050, size=-16, channels=2, buffer=512)
pygame.init()

# Run the main loop
my_game = HitMe()
my_game.start()
# Exit the game if the main loop ends
pygame.quit()

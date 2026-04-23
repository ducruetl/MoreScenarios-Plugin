package net.ducruetl.MoreScenarios.scenarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import net.ducruetl.MoreScenarios.Scenario;

public class HasteyBabies implements Scenario {
    private boolean enabled = false;

    private ArrayList<ShapedRecipe> vanillaRecipes = new ArrayList<>();

    private ArrayList<Material> customResults = new ArrayList<>();

    @Override
    public String getName() {
        return "HasteyBabies";
    }

    @Override
    public ArrayList<String> getIncompatibleScenarios() {
        return new ArrayList<>(Arrays.asList("HasteyBoys"));
    }


    @Override
    public void enable(JavaPlugin plugin) {
        // Remove vanilla tools recipes
        Iterator<Recipe> it = Bukkit.recipeIterator();
        while (it.hasNext()) {
            Recipe r = it.next();
            if (r instanceof ShapedRecipe) {
                ShapedRecipe shaped = (ShapedRecipe) r;
                if (shaped.getResult().getType().name().endsWith("_AXE")
                || shaped.getResult().getType().name().endsWith("_HOE")
                || shaped.getResult().getType().name().endsWith("_PICKAXE")
                || shaped.getResult().getType().name().endsWith("_SPADE")) {
                    vanillaRecipes.add(shaped);
                    it.remove();
                }
            }
        }

        ItemStack item;
        ShapedRecipe recipe;
        
        // Wood Axe
        item = new ItemStack(Material.WOOD_AXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " SX", " S ");
        recipe.setIngredient('X', Material.WOOD);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Stone Axe
        item = new ItemStack(Material.STONE_AXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " SX", " S ");
        recipe.setIngredient('X', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Iron Axe
        item = new ItemStack(Material.IRON_AXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " SX", " S ");
        recipe.setIngredient('X', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Gold Axe
        item = new ItemStack(Material.GOLD_AXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " SX", " S ");
        recipe.setIngredient('X', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Diamond Axe
        item = new ItemStack(Material.DIAMOND_AXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " SX", " S ");
        recipe.setIngredient('X', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Wooden Hoe
        item = new ItemStack(Material.WOOD_HOE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " S ", " S ");
        recipe.setIngredient('X', Material.WOOD);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Stone Hoe
        item = new ItemStack(Material.STONE_HOE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " S ", " S ");
        recipe.setIngredient('X', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Iron Hoe
        item = new ItemStack(Material.IRON_HOE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " S ", " S ");
        recipe.setIngredient('X', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Gold Hoe
        item = new ItemStack(Material.GOLD_HOE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " S ", " S ");
        recipe.setIngredient('X', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Diamond Hoe
        item = new ItemStack(Material.DIAMOND_HOE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" XX", " S ", " S ");
        recipe.setIngredient('X', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Wood Shovel
        item = new ItemStack(Material.WOOD_SPADE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" X ", " S ", " S ");
        recipe.setIngredient('X', Material.WOOD);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Stone Shovel
        item = new ItemStack(Material.STONE_SPADE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" X ", " S ", " S ");
        recipe.setIngredient('X', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Iron Shovel
        item = new ItemStack(Material.IRON_SPADE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" X ", " S ", " S ");
        recipe.setIngredient('X', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Gold Shovel
        item = new ItemStack(Material.GOLD_SPADE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" X ", " S ", " S ");
        recipe.setIngredient('X', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Diamond Shovel
        item = new ItemStack(Material.DIAMOND_SPADE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape(" X ", " S ", " S ");
        recipe.setIngredient('X', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Wood Pickaxe
        item = new ItemStack(Material.WOOD_PICKAXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape("XXX", " S ", " S ");
        recipe.setIngredient('X', Material.WOOD);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Stone Pickaxe
        item = new ItemStack(Material.STONE_PICKAXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape("XXX", " S ", " S ");
        recipe.setIngredient('X', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Iron Pickaxe
        item = new ItemStack(Material.IRON_PICKAXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape("XXX", " S ", " S ");
        recipe.setIngredient('X', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Gold Pickaxe
        item = new ItemStack(Material.GOLD_PICKAXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape("XXX", " S ", " S ");
        recipe.setIngredient('X', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        // Diamond Pickaxe
        item = new ItemStack(Material.DIAMOND_PICKAXE);
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        recipe = new ShapedRecipe(item);
        recipe.shape("XXX", " S ", " S ");
        recipe.setIngredient('X', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);
        customResults.add(recipe.getResult().getType());

        enabled = true;
    }

    @Override
    public void disable() {
        // Remove custom tools recipes
        Iterator<Recipe> it = Bukkit.recipeIterator();
        while (it.hasNext()) {
            Recipe r = it.next();
            if (r instanceof ShapedRecipe) {
                ShapedRecipe recipe = (ShapedRecipe) r;
                if (customResults.contains(recipe.getResult().getType())) {
                    it.remove();
                }
            }
        }

        // Add vanilla recipes back
        for (ShapedRecipe recipe : vanillaRecipes) {
            Bukkit.addRecipe(recipe);
        }

        enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    
}
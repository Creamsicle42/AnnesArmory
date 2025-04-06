package com.creamsicle.annesarmory.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class RapierItem extends TieredItem {

    private final float boostPower;
    private final int boostCooldown;

    public RapierItem(Tier pTier, Item.Properties pProperties, float boostPower, int boostCooldown) {
        super(pTier, pProperties.component(DataComponents.TOOL, createToolProperties()));
        this.boostPower = boostPower;
        this.boostCooldown = boostCooldown;
    }

    private static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }

    public static ItemAttributeModifiers createAttributes(Tier pTier, int pAttackDamage, float pAttackSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double) ((float) pAttackDamage + pTier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double) pAttackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {


        }

    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack pStack = player.getItemInHand(pHand);
        //player.startUsingItem(InteractionHand.MAIN_HAND);

        Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(pStack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
        float yRot = player.getYRot();
        float xRot = player.getXRot();
        float xDelt = -Mth.sin(yRot * (float) (Math.PI / 180.0)) * Mth.cos(xRot * (float) (Math.PI / 180.0));
        float yDelt = -Mth.sin(xRot * (float) (Math.PI / 180.0));
        float zDelt = Mth.cos(yRot * (float) (Math.PI / 180.0)) * Mth.cos(xRot * (float) (Math.PI / 180.0));
        float dLen = Mth.sqrt(xDelt * xDelt + yDelt * yDelt + zDelt * zDelt);
        xDelt *= boostPower / dLen;
        yDelt *= boostPower / dLen;
        zDelt *= boostPower / dLen;

        System.out.println("Player delt before" + player.getDeltaMovement().toString());
        player.push((double) xDelt, (double) yDelt, (double) zDelt);
        System.out.println("Pushing player " + xDelt + " " + yDelt + " " + zDelt);
        System.out.println("Player delt after " + player.getDeltaMovement().toString());
        //player.startAutoSpinAttack(20, 8.0F, pStack);
        if (player.onGround()) {
            player.move(MoverType.SELF, new Vec3(0.0, 0.1, 0.0));
        }


        pLevel.playSound(null, player, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
        player.getCooldowns().addCooldown(pStack.getItem(), boostCooldown);

        return InteractionResultHolder.consume(pStack);
    }

    private static boolean isTooDamagedToUse(ItemStack pStack) {
        return pStack.getDamageValue() >= pStack.getMaxDamage() - 1;
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof ServerPlayer serverplayer && pAttacker.fallDistance > 0) {
            ServerLevel serverlevel = (ServerLevel) pAttacker.level();
            SoundEvent soundevent = SoundEvents.MACE_SMASH_GROUND;
            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1));

            serverlevel.playSound(
                    null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), soundevent, serverplayer.getSoundSource(), 1.0F, 1.0F
            );
        }
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction) || net.minecraftforge.common.ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
    }
}